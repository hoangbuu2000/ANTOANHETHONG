package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.models.PhieuKhamBenh;
import com.dhb.springapp.service.ILoaiBenhService;
import com.dhb.springapp.service.IPhieuKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/disease")
public class DiseaseController {
    @Autowired
    ILoaiBenhService iLoaiBenhService;
    @Autowired
    IPhieuKhamBenhService iPhieuKhamBenhService;

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("diseases", iLoaiBenhService.getAll(LoaiBenh.class));
        return "disease.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false)String id,
                                  ModelMap model) {
        if (id != null && !id.isEmpty()) {
            model.addAttribute("disease", iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(id)));
        }
        else {
            model.addAttribute("disease", new LoaiBenh());
        }
        return "disease.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,
                                     @ModelAttribute("disease") @Valid LoaiBenh loaiBenh,
                                     BindingResult result,
                                     @RequestParam(value = "lang", required = false)String lang) {
        if (!result.hasErrors()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;

                loaiBenh.setDonGia(new BigDecimal(Integer.parseInt(loaiBenh.getDonGiaString())));
                if (id != null && !id.isEmpty())
                    iLoaiBenhService.update(loaiBenh);
                else
                    iLoaiBenhService.insert(loaiBenh);
                return String.format("redirect:/admin/disease%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "disease.add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id,
                         @RequestParam(value = "lang", required = false)String lang) {
        if (id != null && !id.isEmpty()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;

                LoaiBenh loaiBenh = iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(id));
                iLoaiBenhService.delete(loaiBenh);
                return String.format("redirect:/admin/disease%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }
}
