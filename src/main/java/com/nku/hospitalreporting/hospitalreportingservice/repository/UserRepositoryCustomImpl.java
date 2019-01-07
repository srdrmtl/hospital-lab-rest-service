package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;

/**
 *
 * @author serdar
 */
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private EntityManager em;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> searchUsers(String searchText) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        List<Predicate> predicates = new ArrayList();
        predicates.add(cb.or(
                cb.like(cb.lower(root.get("name")), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("address")), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("blood")), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("tcId")), cb.literal("%" + searchText + "%")),
                cb.like(cb.lower(root.get("fileId")), cb.literal("%" + searchText + "%"))
        ));

        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<User> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<User> findByFileId(String fileId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.like(cb.lower(root.get("fileId").as(String.class)), cb.literal("%" + fileId + "%")));
        TypedQuery<User> q = em.createQuery(cq);
        return q.getResultList();
    }

    /**
     * Burada son iki haftanın eklenen hastaları gönderiliyor.
     *
     * @return
     */
    @Override
    public List<User> getHomeResults() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dBegin = dateFormat.format(getDateBeforeTwoWeeks(new Date()));
        String dEnd = dateFormat.format(new Date());
        Date startDate = new Date();
        Date finishDate = new Date();
        try {
            startDate = dateFormat.parse(dBegin);
            finishDate = dateFormat.parse(dEnd);
        } catch (ParseException ex) {
            Logger.getLogger(UserRepositoryCustomImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.between(root.get("date").as(java.sql.Date.class), startDate, finishDate));
        TypedQuery<User> q = em.createQuery(cq);
        return q.getResultList();
    }

    public Date getDateBeforeTwoWeeks(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -14); //2 weeks
        return calendar.getTime();
    }

}
