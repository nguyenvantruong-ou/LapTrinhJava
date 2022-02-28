/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.service;

import com.nvt.hibernatedemo.HibernateUtils;
import com.nvt.pojo.Product;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


/**
 *
 * @author Admin
 */
public class ProductService {
    private static SessionFactory factory = HibernateUtils.getFACTORY();
    
    public List<Product> getProducts(String kw){
        try (Session session = factory.openSession()){
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            
            Root root = q.from(Product.class);
            q.select(root);
            
            if(!kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw));
                q = q.where(p);
            }
            Query query = session.createQuery(q);
            return query.getResultList();
        }
    }
    
    public List<Product> getProducts(BigDecimal fromPrice, BigDecimal toPrice){
        try (Session session = factory.openSession()){
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            
            Root root = q.from(Product.class);
            q.select(root);
            
            if(fromPrice != null && toPrice != null) {
                Predicate p = b.between(root.get("price").as(BigDecimal.class),
                        fromPrice, toPrice);
                q = q.where(p);
            }
            Query query = session.createQuery(q);
            return query.getResultList();
        }
    }
    public List<Product> getProducts(int id){
        try (Session session = factory.openSession()){
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            
            Root root = q.from(Product.class);
            q.select(root);
            
            if(id > 0) {
                Predicate p = b.equal(root.get("id").as(BigDecimal.class),
                        id);
                q = q.where(p);
            }
            Query query = session.createQuery(q);
            return query.getResultList();
        }
    }
}
