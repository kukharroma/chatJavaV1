package com.chat.services;


import com.chat.model.User;

import java.util.List;

public interface IUserService {

    /**
     * Save a user
     * @param user the link of user you want to save
     */
    public void save(User user);

    /**
     *
     * @return  list of users
     */
    public List<User> getAllUsers();

    /**
     *
     * @return list of users
     */
    public List<User> getAllOnlineUser();

    /**
     * Delete all usres
     */
    public void deleteAllUsers();

}
