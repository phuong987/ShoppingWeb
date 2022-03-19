/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.repository.impl;

import com.mycompany.pojos.Comment;
import com.mycompany.pojos.Product;
import com.mycompany.repository.ProductRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dell
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Product> getProducts(String kw, int page){
            Session session = this.sessionFactory.getObject().getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root root = query.from(Product.class);
            query = query.select(root);

            if (kw != null) {
                Predicate p = builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw));

            query = query.where(p);
            }

            Query q =session.createQuery(query);
            
            int max = 2;
            q.setMaxResults(max);
            q.setFirstResult((page-1)*max);
            
            return q.getResultList();
    }

    @Override
    public boolean addOrUpdate(Product product) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
        session.save(product);
        return true;
        } catch(Exception ex){
            System.err.println("=== ADD PRODUCT ERROR ===" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public long countProduct() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From Product");
        
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Product getProductById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public List<Object[]> getMostDiscussProducts(int num) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root rootP = query.from(Product.class);
        Root rootC = query.from(Comment.class);
        
        query = query.where(builder.equal(rootC.get("product"), rootP.get("id")));
        query.multiselect(rootP.get("id"),rootP.get("name"), 
                rootP.get("price"), rootP.get("image"), 
                builder.count(rootP.get("id")));
        
        query = query.groupBy(rootP.get("id"));
        query = query.orderBy(builder.desc(builder.count(rootP.get("id"))),
                builder.desc(rootP.get("id")));
        
        Query q = session.createQuery(query);
        q.setMaxResults(num);
        
        return q.getResultList();
    }
}
