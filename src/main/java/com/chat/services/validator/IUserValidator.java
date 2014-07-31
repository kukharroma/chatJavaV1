package com.chat.services.validator;


import com.chat.model.User;

import java.util.Map;

/**
 * Represents methods which you can implements if
 * you want to be able validate users
 */
public interface IUserValidator {

    /**
     * Validates a whole user
     *
     * @param user link to user you want to validate
     * @return map of Errors
     */
    public Map<String, Object> validateUser(User user);

    /**
     * Validates the name
     *
     * @param user user you want to check the name
     * @param mapErrors map of errors
     */
    public void validateName(User user, Map<String, Object> mapErrors);

    /**
     * Validates the password
     *
     * @param user user you want to check the password
     * @param mapErrors map of errors
     */
    public void validatePassword(User user, Map<String, Object> mapErrors);
}
