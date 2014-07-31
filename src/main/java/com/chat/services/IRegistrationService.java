package com.chat.services;


import com.chat.model.User;

import java.util.Map;

/**
 * Represents methods which you can implements if
 * you want to be able register users
 */
public interface IRegistrationService {

    /**
     * Check the user is valid.
     *
     * @param user user you want to validate
     * @return map of errors
     */
    public Map<String, Object> registration(User user);

    /**
     * Registers a user
     *
     * @param user user you want to register
     */
    public void registerUser(User user);

    /**
     * Sets Authority for user
     * @param user user you want set Authority
     */
    public void setAuthority(User user);
}
