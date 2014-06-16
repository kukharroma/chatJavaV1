package com.chat.services;


import com.chat.model.User;

import java.util.Map;

public interface IRegistrationService {

    public Map<String, Object> registration(User user);

    public void registerUser(User user);

    public void setAuthority(User user);
}
