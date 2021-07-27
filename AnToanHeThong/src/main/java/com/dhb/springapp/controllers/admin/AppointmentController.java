package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.modelview.AddPatient;
import com.dhb.springapp.modelview.Appointment;
import com.dhb.springapp.service.IBenhNhanService;
import com.dhb.springapp.service.ILoaiBenhService;
import com.dhb.springapp.service.IPhieuKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/appointment")
public class AppointmentController {
    @Autowired
    private IPhieuKhamBenhService iPhieuKhamBenhService;
    @Autowired
    private IBenhNhanService iBenhNhanService;
    @Autowired
    private ILoaiBenhService iLoaiBenhService;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("appointments", iPhieuKhamBenhService.getAllAppointmentDESC(format));
        return "appointment.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("patient", new AddPatient());
        return "appointment.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("patient") @Valid AddPatient addPatient,
                             BindingResult result, ModelMap model,
                             @RequestParam(value = "lang", required = false) String lang) throws ParseException {
        if (!result.hasErrors()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;
                iBenhNhanService.themBenhNhanVaPhieuKhamBenh(addPatient, format);
                return String.format("redirect:/admin/appointment%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", e.getMessage());
            }
        }

        return "appointment.add";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable("id") String id, ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        PhieuKhamBenh phieuKhamBenh = iPhieuKhamBenhService.getById(PhieuKhamBenh.class, Integer.parseInt(id));
        AddPatient addPatient = new AddPatient();
        addPatient.setId(phieuKhamBenh.getBenhNhan().getId());
        addPatient.setAppointmentID(String.valueOf(phieuKhamBenh.getId()));
        addPatient.setTen(phieuKhamBenh.getBenhNhan().getTen());
        addPatient.setHo(phieuKhamBenh.getBenhNhan().getHo());
        addPatient.setTuoi(phieuKhamBenh.getBenhNhan().getTuoi());
        addPatient.setGioiTinh(phieuKhamBenh.getBenhNhan().getGioiTinh());
        addPatient.setEmail(phieuKhamBenh.getBenhNhan().getEmail());
        addPatient.setDienThoai(phieuKhamBenh.getBenhNhan().getDienThoai());
        addPatient.setNgaySinh(format.format(phieuKhamBenh.getBenhNhan().getNgaySinh()));
        addPatient.setNgayKham(format.format(phieuKhamBenh.getNgayKham()));
        addPatient.setCaKham(phieuKhamBenh.getCaKhamBenh());
        addPatient.setBacSi(phieuKhamBenh.getBacSi());
        addPatient.setLoaiBenhList(phieuKhamBenh.getDsLoaiBenh());
        addPatient.setThanhToan(phieuKhamBenh.isThanhToan());
        model.addAttribute("patient", addPatient);
        return "appointment.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable("id") String id,
                              @ModelAttribute("patient") @Valid AddPatient editedPatient,
                              BindingResult result, ModelMap model,
                              @RequestParam(value = "lang", required = false)String lang) {
        if (!result.hasErrors()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;
                iBenhNhanService.themBenhNhanVaPhieuKhamBenh(editedPatient, format);
                return String.format("redirect:/admin/appointment%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", e.getMessage());
            }
        }
        return "appointment.edit";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") String id) {
        return "appointment.details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id,
                         @RequestParam(value = "lang", required = false)String lang) {
        String str = "";
        if (lang != null && !lang.isEmpty())
            str = "?lang="+lang;
        if (!id.isEmpty() && id != null) {
            try {
                PhieuKhamBenh phieuKhamBenh = iPhieuKhamBenhService.getById(PhieuKhamBenh.class, Integer.parseInt(id));
                // xóa mối quan hệ giữa pkb và loại bệnh
                for(LoaiBenh l : phieuKhamBenh.getDsLoaiBenh()) {
                    LoaiBenh loaiBenh = iLoaiBenhService.getById(LoaiBenh.class, l.getId());
                    List<PhieuKhamBenh> readyToDelete = new ArrayList<>();
                    loaiBenh.getDsPhieuKhamBenh().forEach(p -> {
                        if (p.getId() == phieuKhamBenh.getId())
                            readyToDelete.add(p);
                    });
                    loaiBenh.getDsPhieuKhamBenh().removeAll(readyToDelete);
                    iLoaiBenhService.update(loaiBenh);
                }
                iPhieuKhamBenhService.delete(phieuKhamBenh);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return String.format("redirect:/admin/appointment%s", str);
    }
}
