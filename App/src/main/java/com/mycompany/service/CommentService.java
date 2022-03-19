/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojos.Comment;
import com.mycompany.pojos.User;

/**
 *
 * @author Dell
 */
public interface CommentService {
    Comment addComment(String content, int productId, User creator);
}
