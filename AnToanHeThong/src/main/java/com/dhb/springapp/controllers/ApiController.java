package com.dhb.springapp.controllers;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.*;
import com.dhb.springapp.modelview.SearchInvoice;
import com.dhb.springapp.modelview.SearchPrescription;
import com.dhb.springapp.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    IToaThuocService iToaThuocService;
    @Autowired
    ICaKhamBenhService iCaKhamBenhService;
    @Autowired
    IBenhNhanService iBenhNhanService;
    @Autowired
    ILoaiBenhService iLoaiBenhService;
    @Autowired
    IBacSiService iBacSiService;
    @Autowired
    IHoaDonService iHoaDonService;
    @Autowired
    IThuocService iThuocService;

    @GetMapping("/ajax")
    public @ResponseBody
    String layBacSi(@RequestParam("date") String date,
                    @RequestParam("shift") int shiftID) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<BacSi> ds = new ArrayList<>();
            iCaKhamBenhService.layBacSiTheoCaKham(shiftID, format.parse(date)).forEach(t -> {
                BacSi b = new BacSi();
                b.setId(t.getId());
                b.setTen(t.getTen());
                b.setHo(t.getHo());
                b.setDienThoai(t.getDienThoai());
                b.setEmail(t.getEmail());
                b.setGioiTinh(t.getGioiTinh());
                b.setNgaySinh(t.getNgaySinh());
                b.setImage(t.getImage());
                b.setQueQuan(t.getQueQuan());
                ds.add(b);
            });
            ajaxResponse = mapper.writeValueAsString(ds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/ajax1")
    public @ResponseBody String layCaKham(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            List<CaKhamBenh> ds = new ArrayList<>();
            iCaKhamBenhService.layCaKhamTheoNgayKham(format.parse(date)).forEach(t -> {
                CaKhamBenh caKhamBenh = new CaKhamBenh();
                caKhamBenh.setId(t.getId());
                caKhamBenh.setTenCa(t.getTenCa());
                caKhamBenh.setMoTa(t.getMoTa());
                ds.add(caKhamBenh);
            });
            ajaxResponse = mapper.writeValueAsString(ds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getDiseaseDetails")
    public @ResponseBody String diseaseDetails(@RequestParam("id")String id) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            Map<Thuoc, Integer> medicines = new HashMap<>();
            iToaThuocService.getById(ToaThuoc.class, id).getDsChiTietToaThuoc().forEach(ct -> {
                Thuoc thuoc = new Thuoc();
                thuoc.setId(ct.getThuoc().getId());
                thuoc.setTenThuoc(ct.getThuoc().getTenThuoc());
                thuoc.setDonGia(ct.getDonGia());
                thuoc.setMoTa(ct.getThuoc().getMoTa());
                thuoc.setDonVi(ct.getThuoc().getDonVi());
                Integer soLuong = Integer.valueOf(ct.getSoLuong());

                medicines.put(thuoc, soLuong);
            });
            ajaxResponse = mapper.writeValueAsString(medicines);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getTotalPatient")
    public @ResponseBody String getTotalPatient(@RequestParam(value = "filter", required = false)String year) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        int[] result;
        if (year != null && !year.isEmpty())
            result = iBenhNhanService.getTotalPatientInMonthOfYear(Integer.parseInt(year));
        else
            result = iBenhNhanService.getTotalPatientInMonthOfYear(new Date().getYear() + 1900);
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getTypeOfDisease")
    public @ResponseBody String getTypeOfDisease() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        List<LoaiBenh> loaiBenhs = iLoaiBenhService.getAll(LoaiBenh.class);
        Map<Integer, String> diseases = new HashMap<>();
        loaiBenhs.forEach(l -> {
            diseases.put(l.getId(), l.getTenBenh());
        });

        try {
            ajaxResponse = mapper.writeValueAsString(diseases);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getTotalPatientOnDisease")
    public @ResponseBody String getTotalPatientOnDisease(@RequestParam("id")String id,
                                                         @RequestParam("year")String year) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        int result = iBenhNhanService.getTotalPatientOnDisease(Integer.parseInt(id), Integer.parseInt(year));
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/getTotalDoctors")
    public @ResponseBody String getTotalDoctors() {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        List<BacSi> result  = new ArrayList<>();
        iBacSiService.getAll(BacSi.class).forEach(b -> {
            BacSi bacSi = new BacSi();
            bacSi.setId(b.getId());
            bacSi.setHo(b.getHo());
            bacSi.setTen(b.getTen());
            bacSi.setGioiTinh(b.getGioiTinh());
            bacSi.setNgaySinh(b.getNgaySinh());
            bacSi.setDienThoai(b.getDienThoai());
            bacSi.setImage(b.getImage());
            bacSi.setQueQuan(b.getQueQuan());
            bacSi.setEmail(b.getEmail());

            result.add(bacSi);
        });

        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getTotalPrescriptionOfDoctor")
    public @ResponseBody String getTotalPatientOfDoctor(@RequestParam(value = "filter", required = false)
                                                                    String filter) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        int[] result = iBacSiService.getTotalPrescriptionOfDoctor(filter);
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getTotalSales")
    public @ResponseBody String getTotalSales(@RequestParam("from")String from,
                                              @RequestParam("to")String to) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        BigDecimal[] result = iHoaDonService.getTotalSalesFromTo(from, to);
        try {
            ajaxResponse = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

    @GetMapping("/getPriceOfDiseases")
    public @ResponseBody String getPriceOfDiseases(@RequestParam("diseases")String diseases) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResult = "";

        String[] arr = diseases.split(";");
        BigDecimal result = new BigDecimal(0);
        for (String a : arr) {
            LoaiBenh loaiBenh = iLoaiBenhService.getById(LoaiBenh.class, Integer.parseInt(a));
            result = result.add(loaiBenh.getDonGia());
        }

        try {
            ajaxResult = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResult;
    }

    @GetMapping("/searchPatient")
    @ResponseBody
    public String searchPatient(@RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "phone", required = false) String phone) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        List<BenhNhan> benhNhanList = new ArrayList<>();
        try {
            if (name != null && !name.isEmpty()) {
                if (phone != null && !phone.isEmpty()) {
                    benhNhanList = iBenhNhanService.castPersistenceToTransient(
                            iBenhNhanService.getBenhNhanTheoTenVaSDT(name, phone));
                }
                else {
                    benhNhanList = iBenhNhanService.castPersistenceToTransient(
                            iBenhNhanService.getBenhNhanTheoTen(name));
                }
            }
            else {
                if (phone != null && !phone.isEmpty())
                    benhNhanList = iBenhNhanService.castPersistenceToTransient(
                            iBenhNhanService.getBenhNhanTheoSDT(phone));
                else
                    benhNhanList = iBenhNhanService.castPersistenceToTransient(
                            iBenhNhanService.getAllOrderBy(BenhNhan.class, "ten", Order.asc));
            }
            ajaxResponse = mapper.writeValueAsString(benhNhanList);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    //hiện tại chưa sử dụng được api này vì còn lỗi các mối quan hệ
    @GetMapping("/searchPrescription")
    @ResponseBody
    public String searchPrescription(@RequestParam(value = "dID", required = false) String dID,
                                @RequestParam(value = "pID", required = false) String pID) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        List<ToaThuoc> toaThuocList;
        try {
            if (dID != null && !dID.isEmpty()) {
                if (pID != null && !pID.isEmpty()) {
                    toaThuocList = iToaThuocService.castPersistenceToTransient(
                            iToaThuocService.getToaThuocTheoBacSiVaBenhNhan(dID, pID));
                }
                else {
                    toaThuocList = iToaThuocService.castPersistenceToTransient(
                            iToaThuocService.getToaThuocTheoBacSi(dID));
                }
            }
            else {
                if (pID != null && !pID.isEmpty())
                    toaThuocList = iToaThuocService.castPersistenceToTransient(
                            iToaThuocService.getToaThuocTheoBenhNhan(pID));
                else
                    toaThuocList = iToaThuocService.castPersistenceToTransient(
                            iToaThuocService.getAllOrderBy(ToaThuoc.class, "ngayKeToa", Order.desc));
            }
            ajaxResponse = mapper.writeValueAsString(toaThuocList);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @GetMapping("/searchMedicines")
    public @ResponseBody String searchMedicines(@RequestParam("name")String name) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResult = "";

        List<Thuoc> result = new ArrayList<>();
        iThuocService.getThuocTheoTen(name).forEach(t -> {
            Thuoc thuoc =  new Thuoc();
            thuoc.setId(t.getId());
            thuoc.setTenThuoc(t.getTenThuoc());
            thuoc.setMoTa(t.getMoTa());
            thuoc.setDonVi(t.getDonVi());
            thuoc.setDonGia(t.getDonGia());

            result.add(thuoc);
        });

        try {
            ajaxResult = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResult;
    }

    @GetMapping("/searchPrescriptionFromTo")
    public @ResponseBody String searchPrescriptionFromTo(@RequestParam("id")String id,
                                                         @RequestParam(value = "from", required = false)String from,
                                                         @RequestParam(value = "to", required = false)String to) {
        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";

        if (!((from == null || from.isEmpty()) && (to == null || to.isEmpty()))) {
            try {
                List<SearchPrescription> result = new ArrayList<>();
                iToaThuocService.getToaThuocFromTo(id, from, to).forEach(t -> {
                    SearchPrescription prescription = new SearchPrescription();
                    prescription.setId(t.getId());
                    prescription.setDate(t.getNgayKeToa());
                    prescription.setDoctorName(t.getBacSi().getTen());
                    prescription.setDoctorImg(t.getBacSi().getImage());
                    prescription.setPatientName(t.getBenhNhan().getTen());
                    prescription.setDiseaseName(t.getLoaiBenh().getTenBenh());
                    prescription.setDiseaseDescription(t.getLoaiBenh().getMoTa());

                    result.add(prescription);
                });
                ajaxResponse = mapper.writeValueAsString(result);
            }
            catch (JsonProcessingException | ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                List<SearchPrescription> result = new ArrayList<>();
                iToaThuocService.getAll(ToaThuoc.class).forEach(t -> {
                    SearchPrescription prescription = new SearchPrescription();
                    prescription.setId(t.getId());
                    prescription.setDate(t.getNgayKeToa());
                    prescription.setDoctorName(t.getBacSi().getTen());
                    prescription.setDoctorImg(t.getBacSi().getImage());
                    prescription.setPatientName(t.getBenhNhan().getTen());
                    prescription.setDiseaseName(t.getLoaiBenh().getTenBenh());
                    prescription.setDiseaseDescription(t.getLoaiBenh().getMoTa());

                    result.add(prescription);
                });
                ajaxResponse = mapper.writeValueAsString(result);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return ajaxResponse;
    }
}
