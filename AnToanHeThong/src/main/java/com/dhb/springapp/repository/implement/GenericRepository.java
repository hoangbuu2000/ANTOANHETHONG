package com.dhb.springapp.repository.implement;

import com.dhb.springapp.enums.Order;
import com.dhb.springapp.models.*;
import com.dhb.springapp.repository.IGenericRepository;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericRepository<T extends Serializable> implements IGenericRepository<T> {
    @Autowired
    @Qualifier("getSessionFactory")
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("passwordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    protected Session currentSession() {
        return this.sessionFactory.getObject().getCurrentSession();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T insert(T t) {
        /*==========HOANG BUU==========*/
        if (t.getClass() == TaiKhoan.class){
            String pPassword = ((TaiKhoan) t).getPassword();
            String cPassword = "";
            String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

            for (int i = 0; i < pPassword.length(); i++) {
                int c = ((key.indexOf(pPassword.charAt(i))) + 16) % key.length();
                cPassword += key.charAt(c);
            }

            ((TaiKhoan) t).setPassword(cPassword);
//            ((TaiKhoan) t).setPassword(bCryptPasswordEncoder.encode(((TaiKhoan) t).getPassword()));
        }

        currentSession().save(t);
        return t;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public T getById(Class<T> type, Object id) {
        try {
            T t = currentSession().get(type, (Serializable) id);

            if (type == TaiKhoan.class) {
                /*=========HOANG BUU==========*/
                //code giai ma password here
                String pPassword = "";
                String cPAssword;
                String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

                cPAssword = ((TaiKhoan) t).getPassword();

                for (int i = 0; i < cPAssword.length(); i++) {
                    int c = ((key.indexOf(cPAssword.charAt(i))) - 16) % key.length();
                    if (c < 0) c += key.length();
                    pPassword += key.charAt(c);
                }
                ((TaiKhoan) t).setPassword(pPassword);
            }

            else if (type == BacSi.class) {
                String pEmail, cEmail, pPhone, cPhone;
                String alphabet1 = "abcdefghijklmnopqrstuvwxyz0123456789@.";
                String alphabet2 = "0123456789";
                int key = 5;
                /*===========QUOC VY============*/
                //code giai ma email va dien thoai bac si here
                //GIAI MA
                cEmail = ((BacSi) t).getEmail();
                pEmail = "";
                for (int i = 0; i < cEmail.length(); i++) {
                    int pIndex = (alphabet1.indexOf(cEmail.charAt(i)) - key) % alphabet1.length();
                    if (pIndex < 0) pIndex += alphabet1.length();
                    pEmail += alphabet1.charAt(pIndex);
                }
                ((BacSi) t).setEmail(pEmail);

                //GIAI MA
                cPhone = ((BacSi) t).getDienThoai();
                pPhone = "";
                for (int i = 0; i < cPhone.length(); i++) {
                    int pIndex = (alphabet2.indexOf(cPhone.charAt(i)) - key) % alphabet2.length();
                    if (pIndex < 0) pIndex += alphabet2.length();
                    pPhone += alphabet2.charAt(pIndex);
                }
                ((BacSi) t).setDienThoai(pPhone);
            }

            return t;
        }
        catch (HibernateError e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T update(T t) {
        if (t.getClass() == TaiKhoan.class){
            ((TaiKhoan) t).setPassword(bCryptPasswordEncoder.encode(((TaiKhoan) t).getPassword()));
        }

        currentSession().saveOrUpdate(t);
        return t;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delete(T t) {
        try {
            currentSession().delete(t);
            return 1;
        }
        catch (HibernateError e) {
            currentSession().getTransaction().rollback();
            return 0;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getAll(Class<T> type) {
        Query q = currentSession().createQuery("From " + type.getName());
        List<T> result = q.getResultList();
        result.forEach(t -> {
            if (t.getClass() == NhanVien.class) {
                /* ===========TU ME========== */
                //code giai ma email va dien thoai here
                String giaima_E = "";
                String giaima_P = "";
                int keyE_1 = 1; int keyE_2 = 2;
                int keyP_1 = 40; int keyP_2 = 50;
                String mahoa_E = ((NhanVien) t).getEmail();
                String mahoa_P = ((NhanVien) t).getDienThoai();

                for (int i=0; i < mahoa_E.length();i++){
                    int temp;
                    if (i%2==0){
                        temp = (int) mahoa_E.charAt(i) - keyE_1;
                    }
                    else {
                        temp = (int) mahoa_E.charAt(i) - keyE_2;
                    }
                    giaima_E += (char)temp;
                }

                for (int i=0; i < mahoa_P.length();i++){
                    int temp;
                    if (i%2==0){
                        temp = (int) mahoa_P.charAt(i) - keyP_1;
                    }
                    else {
                        temp = (int) mahoa_P.charAt(i) - keyP_2;
                    }
                    giaima_P += (char)temp;
                }

                ((NhanVien) t).setEmail(giaima_E);
                ((NhanVien) t).setDienThoai(giaima_P);
            } else if (t.getClass() == Admin.class) {
                /*===========DUY QUANG=============*/
                //code giai ma email va dien thoai here
                String pSDT, cSDT, pEmail, cEmail;
                String key1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@.";
                String key2 = "0123456789";

                cEmail = ((Admin) t).getEmail();
                pEmail = "";
                for (int i = 0; i < cEmail.length(); i++) {
                    int c = ((key1.indexOf(cEmail.charAt(i))) - 16) % key1.length();
                    if (c < 0) c += key1.length();
                    pEmail += key1.charAt(c);
                }
                ((Admin) t).setEmail(pEmail);

                cSDT = ((Admin) t).getDienThoai();
                pSDT = "";
                for (int i = 0; i < cSDT.length(); i++) {
                    int c = ((key2.indexOf(cSDT.charAt(i))) - 16) % key2.length();
                    if (c < 0) c+= key2.length();
                    pSDT += key2.charAt(c);
                }
                ((Admin) t).setDienThoai(pSDT);
            } else if (t.getClass() == BacSi.class) {
                String pEmail, cEmail, pPhone, cPhone;
                String alphabet1 = "abcdefghijklmnopqrstuvwxyz0123456789@.";
                String alphabet2 = "0123456789";
                int key = 5;
                /*===========QUOC VY============*/
                //code giai ma email va dien thoai bac si here
                //GIAI MA
                cEmail = ((BacSi) t).getEmail();
                pEmail = "";
                for (int i = 0; i < cEmail.length(); i++) {
                    int pIndex = (alphabet1.indexOf(cEmail.charAt(i)) - key) % alphabet1.length();
                    if (pIndex < 0) pIndex += alphabet1.length();
                    pEmail += alphabet1.charAt(pIndex);
                }
                ((BacSi) t).setEmail(pEmail);

                //GIAI MA
                cPhone = ((BacSi) t).getDienThoai();
                pPhone = "";
                for (int i = 0; i < cPhone.length(); i++) {
                    int pIndex = (alphabet2.indexOf(cPhone.charAt(i)) - key) % alphabet2.length();
                    if (pIndex < 0) pIndex += alphabet2.length();
                    pPhone += alphabet2.charAt(pIndex);
                }
                ((BacSi) t).setDienThoai(pPhone);
            } else if (t.getClass() == BenhNhan.class) {
                /* ===========TU DUONG============ */
                //code giai ma email va dien thoai here
                String pSDT, cSDT, pEmail, cEmail;
                String key1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@.";
                String key2 = "0123456789";

                cEmail = ((BenhNhan) t).getEmail();
                pEmail = "";
                for (int i = 0; i < cEmail.length(); i++) {
                    int c = ((key1.indexOf(cEmail.charAt(i))) - 16) % key1.length();
                    if (c < 0) c += key1.length();
                    pEmail += key1.charAt(c);
                }
                ((BenhNhan) t).setEmail(pEmail);

                cSDT = ((BenhNhan) t).getDienThoai();
                pSDT = "";
                for (int i = 0; i < cSDT.length(); i++) {
                    int c = ((key2.indexOf(cSDT.charAt(i))) - 16) % key2.length();
                    if (c < 0) c+= key2.length();
                    pSDT += key2.charAt(c);
                }
                ((BenhNhan) t).setDienThoai(pSDT);
            } else if (t.getClass() == TaiKhoan.class) {
                //code giai ma email va dien thoai here
                String pPassword = "";
                String cPAssword;
                String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

                cPAssword = ((TaiKhoan) t).getPassword();

                for (int i = 0; i < cPAssword.length(); i++) {
                    int c = ((key.indexOf(cPAssword.charAt(i))) - 16) % key.length();
                    if (c < 0) c += key.length();
                    pPassword += key.charAt(c);
                }
                ((TaiKhoan) t).setPassword(pPassword);
            }
        });
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getAllOrderBy(Class<T> type, String field, Enum<Order> order) {
        Query q = currentSession().createQuery("From " + type.getName() + " order by " + field + " " + order.name());
        return q.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getTop(Class<T> type, int limit) {
        Query q = currentSession().createQuery("From " + type.getName()).setMaxResults(limit);
        return q.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getTopOrderBy(Class<T> type, int limit, String field, Enum<Order> order) {
        Query q = currentSession().createQuery("From " + type.getName() + " order by " + field + " "
                + order.name()).setMaxResults(limit);
        return q.getResultList();
    }
}

