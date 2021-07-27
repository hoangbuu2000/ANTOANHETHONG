package com.dhb.springapp.repository;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.ToaThuoc;

import java.text.ParseException;
import java.util.List;

public interface IToaThuocRepository extends IGenericRepository<ToaThuoc> {
    boolean addOrUpdate(String id, ToaThuoc toaThuoc, List<ChiTietToaThuoc> chiTietToaThuocList);
    List<ToaThuoc> getToaThuocTheoBacSiVaBenhNhan(String maBacSi, String maBenhNhan);
    List<ToaThuoc> getToaThuocTheoBacSi(String maBacSi);
    List<ToaThuoc> getToaThuocTheoBenhNhan(String maBenhNhan);
    List<ToaThuoc> getToaThuocFromTo(String id, String from, String to) throws ParseException;
}
