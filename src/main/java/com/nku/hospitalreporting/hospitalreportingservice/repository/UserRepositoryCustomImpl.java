/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.User;
import java.util.ArrayList;
import java.util.List;
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
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    
    
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
}
