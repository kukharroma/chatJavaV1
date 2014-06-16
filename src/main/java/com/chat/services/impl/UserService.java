package com.chat.services.impl;

import com.chat.dao.IUserDAO;
import com.chat.model.User;
import com.chat.services.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.List;


public class UserService implements IUserService, UserDetailsService {

    @Resource(name = "userDAO")
    IUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.loadUserByUsername(username);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<User> getAllOnlineUser(){
       return userDAO.getAllOnlineUser();
    }
}
