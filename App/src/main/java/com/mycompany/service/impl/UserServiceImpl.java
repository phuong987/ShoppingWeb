/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mycompany.pojos.User;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public boolean adduser(User user) {
        try {
            String pass = user.getPassword();
            user.setPassword(this.passwordEncoder.encode(pass));
            user.setUserRole(User.USER);
            if (user.getAvatar()==null)
                user.setAvatar(null);
            else{
            Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            user.setAvatar((String) r.get("secure_url"));
            }
            return this.userRepository.adduser(user);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getUsers(String username) {
        return this.userRepository.getUsers(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.getUsers(username);
        if (users.isEmpty())
            throw new UsernameNotFoundException("User does not exist!!!");
        User user = users.get(0);
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getUserRole()));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), auth);
    }

    @Override
    public User getUserById(int i) {
        return this.userRepository.getUserById(i);
    }
    
}
