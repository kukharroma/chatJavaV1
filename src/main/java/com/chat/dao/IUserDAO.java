package com.chat.dao;


import com.chat.model.User;
import com.google.code.morphia.dao.DAO;
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.List;

public interface IUserDAO extends DAO<User, ObjectId> {

    /**
     *
     * @param username name of user you want to load
     * @return instance of user
     */
    public User loadUserByUsername(String username);

    /**
     *
     * @return list of users
     */
    public List<User> getAllOnlineUser();

    /**
     *
     * @return list of users
     */
    public List<User> getAllUsers();

    /**
     * Delete all users
     */
    public void deleteAllUsers();
}
