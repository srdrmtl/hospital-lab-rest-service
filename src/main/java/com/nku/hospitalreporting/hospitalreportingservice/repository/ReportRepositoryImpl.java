package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.Report;
import com.nku.hospitalreporting.hospitalreportingservice.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author serdar
 */
public class ReportRepositoryImpl implements ReportRepositoryCustom {

    EntityManager em;

    public ReportRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Report> searchReports(String searchText) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Report> cq = cb.createQuery(Report.class);
        Root<Report> root = cq.from(Report.class);
        List<Predicate> predicates = new ArrayList();
        predicates.add(cb.or(
                cb.like(cb.lower(root.get("dosyaNo").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("myloblast").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("promyelosit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("myelosit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("metamyelosit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("comak").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("parcali").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("bazofilikSeri").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("eozinofilikSeri").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("lenfosit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("promonosit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("monosit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("plazmaHucresi").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("proeritroblast").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("bazofilikErit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("polikromalofilikErit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("ortokromantofilikErit").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("megakaryositler").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("sellulerite").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("tani").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("raporEden").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("rapor").as(String.class)), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("tarih").as(String.class)), cb.literal("%" + searchText + "%"))
        ));

        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Report> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<Report> findByFileId(String fileId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Report> cq = cb.createQuery(Report.class);
        Root<Report> root = cq.from(Report.class);
        cq.where(cb.like(cb.lower(root.get("dosyaNo").as(String.class)), cb.literal("%" + fileId + "%")));
        TypedQuery<Report> q = em.createQuery(cq);
        return q.getResultList();
    }

}
