package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.Admin;
import com.dhb.springapp.models.BacSi;
import com.dhb.springapp.models.NhanVien;
import com.dhb.springapp.models.TaiKhoan;
import com.dhb.springapp.repository.ITaiKhoanRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaiKhoanRepository extends GenericRepository<TaiKhoan> implements ITaiKhoanRepository {
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public TaiKhoan getTaiKhoanByUsername(String username) {
        Query q = currentSession().createQuery("From TaiKhoan where username=:u");
        q.setParameter("u", username);

        return q.getResultList().isEmpty() ? null : (TaiKhoan) q.getResultList().get(0);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaiKhoan> getTaiKhoanTheoChucVu(String kw) {
        Query q = currentSession().createQuery("From TaiKhoan where role.ten=:c");
        q.setParameter("c", kw);
        return q.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themTaiKhoanVaBacSi(TaiKhoan taiKhoan, BacSi bacSi) {
        TaiKhoan t = insert(taiKhoan);

        /*============QUOC VY=============*/
        //CAESAR
        String alphabet1 = "abcdefghijklmnopqrstuvwxyz0123456789@.";
        String pEmail, cEmail, pPhone, cPhone;
        int key = 5;
        //--> Email <--
        // MA HOA
        pEmail = bacSi.getEmail();
        cEmail = "";
        for (int i = 0; i < pEmail.length(); i++) {
            int cIndex = (alphabet1.indexOf(pEmail.charAt(i)) + key) % alphabet1.length();
            cEmail += alphabet1.charAt(cIndex);
        }
        bacSi.setEmail(cEmail);

        //--> SDT <--
        String alphabet2 = "0123456789";
        //MA HOA
        pPhone = bacSi.getDienThoai();
        cPhone = "";
        for (int i = 0; i < pPhone.length(); i++) {
            int cIndex = (alphabet2.indexOf(pPhone.charAt(i)) + key) % alphabet2.length();
            cPhone += alphabet2.charAt(cIndex);
        }
        bacSi.setDienThoai(cPhone);

        currentSession().save(bacSi);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suaTaiKhoanVaBacSi(TaiKhoan taiKhoan, BacSi bacSi) {
        TaiKhoan t = update(taiKhoan);
        currentSession().saveOrUpdate(bacSi);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themTaiKhoanVaNhanVien(TaiKhoan taiKhoan, NhanVien nhanVien) {
        TaiKhoan t = insert(taiKhoan);

        /* ==============TU ME============ */
        String mahoa_E = "";
        String mahoa_P = "";

        String email = nhanVien.getEmail();
        String phone = nhanVien.getDienThoai();

        int keyE_1 = 1; int keyE_2 = 2;
        int keyP_1 = 40; int keyP_2 = 50;

        //Ma hoa Vegenere Email nhan vien
        for (int i = 0; i < email.length(); i++) {
            int temp;
            if (i % 2 == 0) {
                temp = (int) email.charAt(i) + keyE_1;
            } else {
                temp = (int) email.charAt(i) + keyE_2;
            }
            mahoa_E += (char)temp;
        }

        //Ma hoa Vegenere Phone Number nhan vien
        for (int i = 0; i < phone.length(); i++) {
            int temp;
            if (i % 2 == 0) {
                temp = (int) phone.charAt(i) + keyP_1;
            } else {
                temp = (int) phone.charAt(i) + keyP_2;
            }
            mahoa_P += (char) temp;
        }

        nhanVien.setEmail(mahoa_E);
        nhanVien.setDienThoai(mahoa_P);
        /* =========END MA HOA========= */

        currentSession().save(nhanVien);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suaTaiKhoanVaNhanVien(TaiKhoan taiKhoan, NhanVien nhanVien) {
        TaiKhoan t = update(taiKhoan);
        currentSession().saveOrUpdate(nhanVien);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean themTaiKhoanVaAdmin(TaiKhoan taiKhoan, Admin admin) {
        TaiKhoan t = insert(taiKhoan);

        /* =========DUY QUANG========== */
        String pSDT, cSDT, pEmail, cEmail;

        //Ma hoa email
        String key1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@.";
        pEmail = admin.getEmail();
        cEmail = "";
        for (int i = 0; i < pEmail.length(); i++) {
            int c = ((key1.indexOf(pEmail.charAt(i))) + 16) % key1.length();
            cEmail += key1.charAt(c);
        }
        admin.setEmail(cEmail);

        //Ma hoa SDT
        String key2 = "0123456789";
        pSDT = admin.getDienThoai();
        cSDT = "";
        for (int i = 0; i < pSDT.length(); i++) {
            int c = ((key2.indexOf(pSDT.charAt(i))) + 16) % key2.length();
            cSDT += key2.charAt(c);
        }
        admin.setDienThoai(cSDT);
        /* =========END MA HOA========= */

        currentSession().save(admin);

        return t != null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean suaTaiKhoanVaAdmin(TaiKhoan taiKhoan, Admin admin) {
        TaiKhoan t = update(taiKhoan);
        currentSession().saveOrUpdate(admin);

        return t != null;
    }
}
