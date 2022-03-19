/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.impl;

import com.mycompany.pojos.Comment;
import com.mycompany.pojos.Product;
import com.mycompany.pojos.User;
import com.mycompany.repository.CommentRepository;
import com.mycompany.repository.ProductRepository;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.CommentService;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    
    @Override
    public Comment addComment(String content, int productId, User creator) {
        Product p = this.productRepository.getProductById(productId);
        //User u = this.userRepository.getUserById(2);
       
        Comment c = new Comment();
        c.setContent(content);
        c.setProduct(p);
        c.setUser(creator);
        c.setCreatedDate(new Date());
        return this.commentRepository.addComment(c);
    }
    
}
