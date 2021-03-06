package com.dhb.springapp.controllers.admin;

import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.service.IThuocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/medicine")
public class MedicineController {
    @Autowired
    IThuocService iThuocService;

    @GetMapping("/api/getAll")
    public @ResponseBody String getMedicines() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<Thuoc> result = new ArrayList<>();
            iThuocService.getAll(Thuoc.class).forEach(t -> {
                Thuoc thuoc = new Thuoc();
                thuoc.setId(t.getId());
                thuoc.setTenThuoc(t.getTenThuoc());
                thuoc.setMoTa(t.getMoTa());
                thuoc.setDonVi(t.getDonVi());
                thuoc.setDonGia(t.getDonGia());

                result.add(thuoc);
            });
            ajaxResponse = mapper.writeValueAsString(result);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("medicines", iThuocService.getAll(Thuoc.class));
        return "medicine.index";
    }

    @GetMapping("/addorupdate")
    public String addOrUpdateView(@RequestParam(value = "id", required = false) String id,
                                  ModelMap model) {
        if (id != null && !id.isEmpty()) {
            model.addAttribute("medicine", iThuocService.getById(Thuoc.class, Integer.parseInt(id)));
        }
        else
            model.addAttribute("medicine", new Thuoc());
        return "medicine.add";
    }

    @PostMapping("/addorupdate")
    public String addOrUpdateProcess(@RequestParam(value = "id", required = false)String id,
                                     @ModelAttribute("medicine") @Valid Thuoc thuoc,
                                     BindingResult result, ModelMap model,
                                     @RequestParam(value = "lang", required = false)String lang) {
        if (!result.hasErrors()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;

                thuoc.setDonGia(new BigDecimal(Integer.parseInt(thuoc.getDonGiaString())));
                if (id != null && !id.isEmpty())
                    iThuocService.update(thuoc);
                else
                    iThuocService.insert(thuoc);
                return String.format("redirect:/admin/medicine%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "medicine.add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id")String id,
                         @RequestParam(value = "lang", required = false)String lang) {
        if (id != null && !id.isEmpty()) {
            try {
                String str = "";
                if (lang != null && !lang.isEmpty())
                    str = "?lang="+lang;

                iThuocService.delete(iThuocService.getById(Thuoc.class, Integer.parseInt(id)));
                return String.format("redirect:/admin/medicine%s", str);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "404";
    }

    @PostMapping("/deleteAjax")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAjax(@RequestParam("id")String id) {
        if (id != null && !id.isEmpty()) {
            try {
                iThuocService.delete(iThuocService.getById(Thuoc.class, Integer.parseInt(id)));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
