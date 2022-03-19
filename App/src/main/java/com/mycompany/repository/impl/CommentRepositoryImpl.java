/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.repository.impl;

import com.mycompany.pojos.Comment;
import com.mycompany.repository.CommentRepository;
import org.hibernate.HibernateException;
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
public class CommentRepositoryImpl implements CommentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public Comment addComment(Comment c) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(c);
            return c;
        } catch (HibernateException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
}
