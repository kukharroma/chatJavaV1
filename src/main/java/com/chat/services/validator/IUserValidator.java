package com.chat.services.validator;


import com.chat.model.User;

import java.util.Map;

public interface IUserValidator {

    /**
     *
     * @param user link to user you want to validate
     * @return map of Errors
     */
    public Map<String, Object> validateUser(User user);

    /**
     *
     * @param user link to user you want to check the name
     * @param mapErrors map of errors
     */
    public void validateName(User user, Map<String, Object> mapErrors);

    /**
     *
     * @param user link to user you want to check the password
     * @param mapErrors map of errors
     */
    public void validatePassword(User user, Map<String, Object> mapErrors);
}
