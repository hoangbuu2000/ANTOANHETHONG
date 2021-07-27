package com.dhb.springapp.modelview;

import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.CaKhamBenh;
import com.dhb.springapp.models.LoaiBenh;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Set;

public class AddPatient {
    private String id;
    private String appointmentID;
    @Length(min = 2, max = 30, message = "{err.lastName}")
    private String ho;
    @Length(min = 2, max = 30, message = "{err.firstName}")
    private String ten;
    @Min(value = 1, message = "{err.age.min}")
    @Max(value = 100, message = "{err.age.max}")
    private int tuoi;
    @NotEmpty(message = "{err.common}")
    @Email(message = "{err.email}")
    private String email;
    @Pattern(regexp = "[0-9]{10}", message = "{err.phone}")
    private String dienThoai;
    @NotEmpty(message = "{err.common}")
    private String ngaySinh;
    @NotEmpty(message = "{err.common}")
    private String gioiTinh;
    @NotNull(message = "{err.common}")
    private CaKhamBenh caKham;
    private BacSi bacSi;
    @Size(min = 1, message = "{err.disease}")
    private Set<LoaiBenh> loaiBenhList;
    @NotEmpty(message = "{err.common}")
    private String ngayKham;
    @NotNull(message = "{err.common}")
    private boolean thanhToan;

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public CaKhamBenh getCaKham() {
        return caKham;
    }

    public void setCaKham(CaKhamBenh caKham) {
        this.caKham = caKham;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public Set<LoaiBenh> getLoaiBenhList() {
        return loaiBenhList;
    }

    public void setLoaiBenhList(Set<LoaiBenh> loaiBenhList) {
        this.loaiBenhList = loaiBenhList;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }
}
