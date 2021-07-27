package com.dhb.springapp.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "thuoc")
public class Thuoc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "{err.common}")
    @Column(name = "TenThuoc", length = 45, nullable = false)
    private String tenThuoc;
    @Column(name = "MoTa", nullable = false)
    @NotEmpty(message = "{err.common}")
    private String moTa;
    @Column(name = "DonGia", nullable = false)
    private BigDecimal donGia;
    @Transient
    @NotNull(message = "{err.common}")
    @Pattern(regexp = "[1-9][0-9]*", message = "{err.price}")
    private String donGiaString;
    @Column(name = "DonVi", length = 45, nullable = false)
    @NotEmpty(message = "{err.common}")
    private String donVi;
    @OneToMany(mappedBy = "thuoc", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ChiTietToaThuoc> dsChiTietToaThuoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public Set<ChiTietToaThuoc> getDsChiTietToaThuoc() {
        return dsChiTietToaThuoc;
    }

    public void setDsChiTietToaThuoc(Set<ChiTietToaThuoc> dsChiTietToaThuoc) {
        this.dsChiTietToaThuoc = dsChiTietToaThuoc;
    }

    @Override
    public String toString() {
        return "Thuoc{" +
                "id=" + id +
                ", tenThuoc='" + tenThuoc + '\'' +
                ", moTa='" + moTa + '\'' +
                ", donGia=" + donGia +
                ", donVi='" + donVi + '\'' +
                ", dsChiTietToaThuoc=" + dsChiTietToaThuoc +
                '}';
    }

    public String getDonGiaString() {
        return donGiaString;
    }

    public void setDonGiaString(String donGiaString) {
        this.donGiaString = donGiaString;
    }
}
