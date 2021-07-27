package com.dhb.springapp.repository;

import com.dhb.springapp.models.BenhNhan;
import com.dhb.springapp.models.LoaiBenh;
import com.dhb.springapp.models.PhieuKhamBenh;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IBenhNhanRepository extends IGenericRepository<BenhNhan> {
    List<BenhNhan> getBenhNhanTheoTen(String ten);
    List<BenhNhan> getTopBenhNhanTheoTen(int limit, String name);
    List<BenhNhan> getTopNewBenhNhan(int limit);
    int getSoLuongPhieuKhamBenhCuaBenhNhan(BenhNhan benhNhan);
    boolean themBenhNhanVaPhieuKhamBenh(BenhNhan benhNhan, PhieuKhamBenh phieuKhamBenh, Set<LoaiBenh> loaiBenhs);
    List<Object[]> getBenhAnTheoBenhNhan(String id);
    List<BenhNhan> getBenhNhanCoLichKhamTheoThoiGianChoTruoc(Date date, int maCa);
    List<BenhNhan> getBenhNhanTheoTenVaSDT(String ten, String phone);
    List<BenhNhan> getBenhNhanTheoSDT(String phone);
}
