/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojos.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Dell
 */
public interface UserService extends UserDetailsService{
    boolean adduser(User user);
    List<User> getUsers (String username);
    User getUserById(int userId);
}
