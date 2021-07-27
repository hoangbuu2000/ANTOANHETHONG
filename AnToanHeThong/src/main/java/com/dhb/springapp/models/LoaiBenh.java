package com.dhb.springapp.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "loaibenh")
public class LoaiBenh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "{err.common}")
    @Column(name = "TenBenh", length = 45, nullable = false)
    private String tenBenh;
    @NotEmpty(message = "{err.common}")
    @Column(name = "MoTa", nullable = false)
    private String moTa;
    @Column(name = "DonGia", nullable = false)
    private BigDecimal donGia;
    @Transient
    @NotNull(message = "{err.common}")
    @Pattern(regexp = "[1-9][0-9]*", message = "{err.price}")
    private String donGiaString;
    @OneToMany(mappedBy = "loaiBenh", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ToaThuoc> dsToaThuoc;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "chitietphieukhambenh",
            joinColumns = {
                    @JoinColumn(name = "MaLoaiBenh")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "MaPhieuKhamBenh")
            }
    )
    private Set<PhieuKhamBenh> dsPhieuKhamBenh = new HashSet<>();

    public Set<PhieuKhamBenh> getDsPhieuKhamBenh() {
        return dsPhieuKhamBenh;
    }

    public void setDsPhieuKhamBenh(Set<PhieuKhamBenh> dsPhieuKhamBenh) {
        this.dsPhieuKhamBenh = dsPhieuKhamBenh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBenh() {
        return tenBenh;
    }

    public void setTenBenh(String tenBenh) {
        this.tenBenh = tenBenh;
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

    public Set<ToaThuoc> getDsToaThuoc() {
        return dsToaThuoc;
    }

    public void setDsToaThuoc(Set<ToaThuoc> dsToaThuoc) {
        this.dsToaThuoc = dsToaThuoc;
    }

    public String getDonGiaString() {
        return donGiaString;
    }

    public void setDonGiaString(String donGiaString) {
        this.donGiaString = donGiaString;
    }
}
