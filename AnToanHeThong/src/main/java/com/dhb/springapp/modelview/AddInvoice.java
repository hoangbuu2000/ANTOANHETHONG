package com.dhb.springapp.modelview;

import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.Thuoc;
import com.dhb.springapp.models.ToaThuoc;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class AddInvoice {
    private String id;
    @NotNull(message = "{err.common}")
    private NhanVien employee;
    @NotNull(message = "{err.common}")
    private ToaThuoc prescription;
    @NotEmpty(message = "{err.common}")
    private String date;
    private Map<Thuoc, Integer> medicines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NhanVien getEmployee() {
        return employee;
    }

    public void setEmployee(NhanVien employee) {
        this.employee = employee;
    }

    public ToaThuoc getPrescription() {
        return prescription;
    }

    public void setPrescription(ToaThuoc prescription) {
        this.prescription = prescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<Thuoc, Integer> getMedicines() {
        return medicines;
    }

    public void setMedicines(Map<Thuoc, Integer> medicines) {
        this.medicines = medicines;
    }
}
