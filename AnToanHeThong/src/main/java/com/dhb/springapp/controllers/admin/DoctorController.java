package com.dhb.springapp.controllers.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.modelview.AddDoctor;
import com.dhb.springapp.service.IBacSiService;
import com.dhb.springapp.service.ILoaiBenhService;
import com.dhb.springapp.service.IPhieuKhamBenhService;
import com.dhb.springapp.service.ITaiKhoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/doctor")
public class DoctorController {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
       "cloud_name", "dk5jgf3xj",
       "api_key", "781767664334152",
       "api_secret", "JmhAHxHXKWsrnrfWnsRiMg_JsSw"
    ));

    @Autowired
    private IBacSiService iBacSiService;
    @Autowired
    private ITaiKhoanService iTaiKhoanService;
    @Autowired
    private IPhieuKhamBenhService iPhieuKhamBenhService;
    @Autowired
    private ILoaiBenhService iLoaiBenhService;

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("danhSachBacSi", iBacSiService.getAll(BacSi.class));
        return "doctor.index";
    }

    @GetMapping("/add")
    public String addView(ModelMap model) {
        model.addAttribute("doctor", new AddDoctor());
        return "doctor.add";
    }

    @PostMapping("/add")
    public String addProcess(@ModelAttribute("doctor") @Valid AddDoctor addDoctor,
                             BindingResult result, ModelMap model,
                             @RequestParam(value = "lang", required = false)String lang) throws ParseException {
        if (!result.hasErrors()) {
            if(iTaiKhoanService.checkPassword(addDoctor)) {
                if (iTaiKhoanService.checkExistedUsername(addDoctor)) {
                    try {
                        String str = "";
                        if (lang != null && !lang.isEmpty())
                            str = "?lang="+lang;

                        MultipartFile img = addDoctor.getImage();
                        Map uploadResult = new HashMap();
                        String path = "";
                        if (img != null && !img.isEmpty()) {
                            uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                        "public_id", "my_folder/" + addDoctor.getUsername()));
                            path = uploadResult.get("url").toString();
                        }
                        iTaiKhoanService.themTaiKhoanVaBacSi(path, addDoctor);
                        return String.format("redirect:/admin/doctor%s", str);
                    }
                    catch (Exception e) {
                        model.addAttribute("message", e.getMessage());
                    }
                }
                else {
                    model.addAttribute("message", "Tai khoan da ton tai");
                }
            }
            else {
                model.addAttribute("message", "Xac nhan mat khau khong dung");
            }
        }

        return "doctor.add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") String id, ModelMap model) {
        BacSi bacSi = iBacSiService.getById(BacSi.class, id);
        return "doctor.details";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable(name = "id") String id, ModelMap model) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        BacSi bacSi = iBacSiService.getById(BacSi.class, id);
        TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);

        AddDoctor doctor = iBacSiService.castDoctorToModelView(bacSi, taiKhoan, format);

        model.addAttribute("doctor", doctor);
        model.addAttribute("id", id);
        return "doctor.edit";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable(name = "id") String id,
                              @ModelAttribute("doctor") @Valid AddDoctor editedDoctor,
                              BindingResult result,
                              ModelMap model,
                              @RequestParam(value = "lang", required = false) String lang) {
        if (!result.hasErrors()) {
                if(iTaiKhoanService.checkPassword(editedDoctor)) {
                    if (iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)
                            || (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor))
                            && iTaiKhoanService.checkExistedUsername(editedDoctor)) {

                        try {
                            String str = "";
                            if (lang != null && !lang.isEmpty())
                                str = "?lang="+lang;

                            TaiKhoan taiKhoan = iTaiKhoanService.getById(TaiKhoan.class, id);
                            MultipartFile img = editedDoctor.getImage();
                            String path = "";
                            Map uploadResult = new HashMap();
                            if (img != null && !img.isEmpty()) {
                                if (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)) {
                                    cloudinary.uploader().destroy("my_folder/"+taiKhoan.getUsername(),
                                            ObjectUtils.emptyMap());
                                }
                                try {
                                    uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.asMap(
                                            "public_id", "my_folder/" + editedDoctor.getUsername()));
                                    path = uploadResult.get("url").toString();
                                }
                                catch (IllegalStateException | IOException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                            else {
                                path = taiKhoan.getBacSi().getImage();
                                if (!iTaiKhoanService.checkNoChangeUsername(id, editedDoctor)) {
                                    uploadResult = cloudinary.uploader().rename("my_folder/"+taiKhoan.getUsername(),
                                            "my_folder/"+editedDoctor.getUsername(),
                                            ObjectUtils.emptyMap());
                                    path = uploadResult.get("url").toString();
                                }
                            }
                            iTaiKhoanService.suaTaiKhoanVaBacSi(id, path, editedDoctor);
                            return String.format("redirect:/admin/doctor%s", str);
                        }
                        catch (Exception e) {
                            model.addAttribute("message", e.getMessage());
                        }
                    }
                    else {
                        model.addAttribute("messages", "Tai khoan da ton tai");
                    }
                }
                else {
                    model.addAttribute("message", "Xac nhan mat khau khong dung");
                }
        }
        model.addAttribute("doctor", editedDoctor);
        model.addAttribute("id", id);
        return "doctor.edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable(name = "id") String id,
                                @RequestParam(value = "lang", required = false)String lang) {
        if (id != null && !id.isEmpty()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;

                BacSi bacSi = iBacSiService.getById(BacSi.class, id);
                //xóa mối quan hệ giữa pkb và loại bệnh
                bacSi.getDsPhieuKhamBenh().forEach(p -> {
                    for(LoaiBenh l : p.getDsLoaiBenh()) {
                        LoaiBenh loaiBenh = iLoaiBenhService.getById(LoaiBenh.class, l.getId());
                        List<PhieuKhamBenh> readyToDelete = new ArrayList<>();
                        loaiBenh.getDsPhieuKhamBenh().forEach(p1 -> {
                            if (p1.getId() == p.getId())
                                readyToDelete.add(p1);
                        });
                        loaiBenh.getDsPhieuKhamBenh().removeAll(readyToDelete);
                        iLoaiBenhService.update(loaiBenh);
                    }
                });

                iBacSiService.delete(bacSi);
                return String.format("redirect:/admin/doctor%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "doctor.index";
    }
}
