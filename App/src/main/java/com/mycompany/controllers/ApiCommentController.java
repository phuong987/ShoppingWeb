/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.pojos.Comment;
import com.mycompany.pojos.User;
import com.mycompany.service.CommentService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
public class ApiCommentController {
    @Autowired
    private CommentService commentService;
    
    @PostMapping(path = "/api/add-comment", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Comment> addComment(@RequestBody Map<String,String> params,
            HttpSession session){
        User u = (User) session.getAttribute("currentUser");
        if (u !=null)
            try{
            String content = params.get("content");
            int productId = Integer.parseInt(params.get("productId"));

            Comment c = this.commentService.addComment(content, productId, u);

            return new ResponseEntity<>(c, HttpStatus.CREATED);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
