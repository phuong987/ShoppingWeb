/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.repository;

import com.mycompany.pojos.User;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface UserRepository {
    boolean adduser(User user);
    List<User> getUsers (String username);
    User getUserById(int userId);
}
