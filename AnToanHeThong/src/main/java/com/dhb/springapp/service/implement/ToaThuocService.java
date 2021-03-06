package com.dhb.springapp.service.implement;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddPrescription;
import com.dhb.springapp.repository.IGenericRepository;
import com.dhb.springapp.repository.IThuocRepository;
import com.dhb.springapp.repository.IToaThuocRepository;
import com.dhb.springapp.service.IToaThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ToaThuocService extends GenericService<ToaThuoc> implements IToaThuocService {
    private IToaThuocRepository toaThuocRepository;
    @Autowired
    private IThuocRepository iThuocRepository;

    @Autowired
    public ToaThuocService(@Qualifier("toaThuocRepository")IGenericRepository<ToaThuoc> iGenericRepository) {
        super(iGenericRepository);
        this.toaThuocRepository = (IToaThuocRepository) iGenericRepository;
    }

    @Override
    public AddPrescription castToModelView(ToaThuoc toaThuoc, SimpleDateFormat format) {
        AddPrescription result = new AddPrescription();
        Map<Thuoc, Integer> medicines = new HashMap<>();
        result.setId(toaThuoc.getId());
        result.setDoctor(toaThuoc.getBacSi());
        result.setPatient(toaThuoc.getBenhNhan());
        result.setDate(format.format(toaThuoc.getNgayKeToa()));
        result.setDisease(toaThuoc.getLoaiBenh());
        toaThuoc.getDsChiTietToaThuoc().forEach(t -> {
            medicines.put(t.getThuoc(), t.getSoLuong());
        });
        result.setMedicines(medicines);
        return result;
    }

    @Override
    public void addOrUpdate(String id, AddPrescription addPrescription, SimpleDateFormat format) throws Exception {
        if (id != null && !id.isEmpty()) {
            // update
            ToaThuoc toaThuoc = getById(ToaThuoc.class, id);
            toaThuoc.setBacSi(addPrescription.getDoctor());
            toaThuoc.setBenhNhan(addPrescription.getPatient());
            toaThuoc.setLoaiBenh(addPrescription.getDisease());
            toaThuoc.setNgayKeToa(format.parse(addPrescription.getDate()));

            List<ChiTietToaThuoc> chiTietToaThuocList = setUpChiTietToaThuoc(addPrescription, toaThuoc);
            if (!toaThuocRepository.addOrUpdate(id, toaThuoc, chiTietToaThuocList))
                throw new Exception("Giao tac them that bai");

        }
        else {
            // insert
            ToaThuoc toaThuoc = new ToaThuoc();
            toaThuoc.setId(UUID.randomUUID().toString());
            toaThuoc.setBacSi(addPrescription.getDoctor());
            toaThuoc.setBenhNhan(addPrescription.getPatient());
            toaThuoc.setNgayKeToa(format.parse(addPrescription.getDate()));
            toaThuoc.setLoaiBenh(addPrescription.getDisease());

            List<ChiTietToaThuoc> chiTietToaThuocList = setUpChiTietToaThuoc(addPrescription, toaThuoc);
            if (!toaThuocRepository.addOrUpdate(id, toaThuoc, chiTietToaThuocList))
                throw new Exception("Giao tac them that bai");
        }
    }

    @Override
    public List<ToaThuoc> castPersistenceToTransient(List<ToaThuoc> toaThuocList) {
        List<ToaThuoc> result = new ArrayList<>();
        toaThuocList.forEach(t -> {
            ToaThuoc toaThuoc = new ToaThuoc();
            toaThuoc.setId(t.getId());
            toaThuoc.setBacSi(t.getBacSi());
            toaThuoc.setBenhNhan(t.getBenhNhan());
            toaThuoc.setNgayKeToa(t.getNgayKeToa());
            toaThuoc.setLoaiBenh(t.getLoaiBenh());
            result.add(toaThuoc);
        });
        return result;
    }

    @Override
    public List<ToaThuoc> getToaThuocTheoBacSiVaBenhNhan(String maBacSi, String maBenhNhan) {
        return toaThuocRepository.getToaThuocTheoBacSiVaBenhNhan(maBacSi, maBenhNhan);
    }

    @Override
    public List<ToaThuoc> getToaThuocTheoBacSi(String maBacSi) {
        return toaThuocRepository.getToaThuocTheoBacSi(maBacSi);
    }

    @Override
    public List<ToaThuoc> getToaThuocTheoBenhNhan(String maBenhNhan) {
        return toaThuocRepository.getToaThuocTheoBenhNhan(maBenhNhan);
    }

    @Override
    public List<ToaThuoc> getToaThuocFromTo(String id, String from, String to) throws ParseException {
        return toaThuocRepository.getToaThuocFromTo(id, from, to);
    }

    private List<ChiTietToaThuoc> setUpChiTietToaThuoc(AddPrescription addPrescription, ToaThuoc toaThuoc) {
        List<ChiTietToaThuoc> chiTietToaThuocList = new ArrayList<>();
        AtomicBoolean duplicated = new AtomicBoolean(false);
        addPrescription.getMedicines().forEach((thuoc, soLuong) -> {
            Thuoc th = iThuocRepository.getById(Thuoc.class, thuoc.getId());
            chiTietToaThuocList.stream().filter(ct -> ct.getToaThuoc().getId().equals(toaThuoc.getId()) &&
                    ct.getThuoc().getId() == th.getId()).forEach(ct -> {
                ct.setSoLuong(ct.getSoLuong() + soLuong);
                duplicated.set(true);
            });
            if (!duplicated.get()) {
                ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
                chiTietToaThuoc.setToaThuoc(toaThuoc);
                chiTietToaThuoc.setThuoc(th);
                chiTietToaThuoc.setDonGia(th.getDonGia());
                chiTietToaThuoc.setSoLuong(soLuong);
                chiTietToaThuoc.setThanhTien(th.getDonGia().multiply(new BigDecimal(soLuong)));

                chiTietToaThuocList.add(chiTietToaThuoc);
            }
        });

        return chiTietToaThuocList;
    }
}
