package com.dhb.springapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "chitietcakhambenh")
public class ChiTietCaKhamBenh implements Serializable {
    @Id
    @Column(name = "NgayKhamBenh")
    @Temporal(TemporalType.DATE)
    private Date ngayKhamBenh;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaCa")
    @NotNull(message = "{err.common}")
    private CaKhamBenh caKhamBenh;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaBacSi")
    @NotNull(message = "{err.common}")
    private BacSi bacSi;

    @Transient
    @NotEmpty(message = "{err.common}")
    private String ngayKham;

    @Override
    public String toString() {
        return "ChiTietCaKhamBenh{" +
                "ngayKhamBenh=" + ngayKhamBenh +
                ", caKhamBenh=" + caKhamBenh +
                ", bacSi=" + bacSi +
                '}';
    }

    public Date getNgayKhamBenh() {
        return ngayKhamBenh;
    }

    public void setNgayKhamBenh(Date ngayKhamBenh) {
        this.ngayKhamBenh = ngayKhamBenh;
    }

    public CaKhamBenh getCaKhamBenh() {
        return caKhamBenh;
    }

    public void setCaKhamBenh(CaKhamBenh caKhamBenh) {
        this.caKhamBenh = caKhamBenh;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }
}
