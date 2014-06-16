package com.chat.dao;


import com.chat.model.User;
import com.google.code.morphia.dao.DAO;
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.List;

public interface IUserDAO extends DAO<User, ObjectId> {

    public User loadUserByUsername(String username);

    public List<User> getAllOnlineUser();

    public List<User> getAllUsers();

}
