package com.dhb.springapp.service;

import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.modelview.AddPrescription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public interface IToaThuocService extends IGenericService<ToaThuoc> {
    AddPrescription castToModelView(ToaThuoc toaThuoc, SimpleDateFormat format);
    void addOrUpdate(String id, AddPrescription addPrescription, SimpleDateFormat format) throws Exception;
    List<ToaThuoc> castPersistenceToTransient(List<ToaThuoc> toaThuocList);
    List<ToaThuoc> getToaThuocTheoBacSiVaBenhNhan(String maBacSi, String maBenhNhan);
    List<ToaThuoc> getToaThuocTheoBacSi(String maBacSi);
    List<ToaThuoc> getToaThuocTheoBenhNhan(String maBenhNhan);
    List<ToaThuoc> getToaThuocFromTo(String id, String from, String to) throws ParseException;
}
