package com.chat.services;


import com.chat.model.User;

import java.util.List;

public interface IUserService {

    public void save(User user);

    public List<User> getAllUsers();

    public List<User> getAllOnlineUser();

    public void deleteAllUsers();

}
