package com.dhb.springapp.repository.implement;

import com.dhb.springapp.models.ChiTietToaThuoc;
import com.dhb.springapp.models.HoaDon;
import com.dhb.springapp.models.ToaThuoc;
import com.dhb.springapp.repository.IChiTietToaThuocRepository;
import com.dhb.springapp.repository.IToaThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ToaThuocRepository extends GenericRepository<ToaThuoc> implements IToaThuocRepository {
    @Autowired
    IChiTietToaThuocRepository iChiTietToaThuocRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean addOrUpdate(String id, ToaThuoc toaThuoc,
                               List<ChiTietToaThuoc> chiTietToaThuocList) {
        ToaThuoc result;
        if (id != null && !id.isEmpty()) {
            result = update(toaThuoc);

            chiTietToaThuocList.forEach(t -> {
                iChiTietToaThuocRepository.update(t);
            });

        }
        else {
            result = insert(toaThuoc);

            chiTietToaThuocList.forEach(t -> {
                iChiTietToaThuocRepository.insert(t);
            });

        }
        return result != null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ToaThuoc> getToaThuocTheoBacSiVaBenhNhan(String maBacSi, String maBenhNhan) {
        List<ToaThuoc> result = currentSession().createQuery("From ToaThuoc where bacSi.id=:dID " +
                "and benhNhan.id=:pID")
                .setParameter("dID", maBacSi)
                .setParameter("pID", maBenhNhan)
                .getResultList();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ToaThuoc> getToaThuocTheoBacSi(String maBacSi) {
        List<ToaThuoc> result = currentSession().createQuery("From ToaThuoc where bacSi.id=:dID")
                .setParameter("dID", maBacSi)
                .getResultList();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ToaThuoc> getToaThuocTheoBenhNhan(String maBenhNhan) {
        List<ToaThuoc> result = currentSession().createQuery("From ToaThuoc where benhNhan.id=:pID")
                .setParameter("pID", maBenhNhan)
                .getResultList();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ToaThuoc> getToaThuocFromTo(String id, String from, String to) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<ToaThuoc> q = builder.createQuery(ToaThuoc.class);
        Root<ToaThuoc> root = q.from(ToaThuoc.class);
        q.select(root);

        q.where(builder.equal(root.get("benhNhan").get("id").as(String.class), id));
        if (from != null && !from.isEmpty() && to != null && !to.isEmpty())
            q.where(builder.between(root.get("ngayKeToa").as(Date.class), format.parse(from),
                    format.parse(to)));
        else if ((from != null && !from.isEmpty()) && (to == null || to.isEmpty()))
            q.where(builder.greaterThanOrEqualTo(root.get("ngayKeToa").as(Date.class), format.parse(from)));
        else if ((from == null || from.isEmpty()) && (to != null && !to.isEmpty()))
            q.where(builder.lessThanOrEqualTo(root.get("ngayKeToa").as(Date.class), format.parse(to)));
        return currentSession().createQuery(q).getResultList();
    }
}
