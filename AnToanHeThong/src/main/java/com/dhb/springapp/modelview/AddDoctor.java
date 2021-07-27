package com.dhb.springapp.modelview;

import com.dhb.springapp.validators.ConfirmPassword;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddDoctor {
    @Length(min = 6, max = 20, message = "{err.username}")
    private String username;
    @Length(min = 6, max = 20, message = "{err.password}")
    private String password;
    @Length(min = 6, max = 20, message = "{err.password}")
    private String confirmPassword;
    @Length(min = 2, max = 30, message = "{err.lastName}")
    private String ho;
    @Length(min = 2, max = 30, message = "{err.firstName}")
    private String ten;
    @NotEmpty(message = "{err.common}")
    private String ngaySinh;
    @Pattern(regexp = "[0-9]{10}", message = "{err.phone}")
    private String dienThoai;
    @NotEmpty(message = "{err.common}")
    private String gioiTinh;
    @Email(message = "{err.email}")
    @NotEmpty(message = "{err.common}")
    private String email;
    @NotEmpty(message = "{err.common}")
    private String queQuan;
    private MultipartFile image;
    private String urlImg;
    @NotNull(message = "{err.common}")
    private boolean active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
