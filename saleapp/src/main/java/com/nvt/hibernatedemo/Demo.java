/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.hibernatedemo;

import com.nvt.pojo.Category;
import com.nvt.pojo.Product;
import com.nvt.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class Demo {
    public static void main(String[] args) {
        try (Session session = HibernateUtils.getFACTORY().openSession()) {
            //Query<Product> query = session.createQuery("FROM Product");
            //List<Product> cat = query.list();
            
            ProductService s = new ProductService();
            //List<Product> products = s.getProducts("Galaxy");
            List<Product> products = s.getProducts(2);
            products.forEach(c -> System.out.printf("%d - %s \n",c.getId(), c.getName()));
        }
    }
}
