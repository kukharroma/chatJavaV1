package com.chat.services;


import com.chat.model.User;

import java.util.Map;

public interface IRegistrationService {

    /**
     *
     * @param user a link to user you want to validate
     * @return map of errors
     */
    public Map<String, Object> registration(User user);

    /**
     *
     * @param user link to user you want to register
     */
    public void registerUser(User user);

    /**
     *
     * @param user a link to user you want set Authority
     */
    public void setAuthority(User user);
}
