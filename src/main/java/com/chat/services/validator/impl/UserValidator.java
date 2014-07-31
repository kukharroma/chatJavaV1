package com.chat.services.validator.impl;


import com.chat.model.User;
import com.chat.services.IUserService;
import com.chat.services.validator.IUserValidator;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents methods with which you can validate users
 */
public class UserValidator implements IUserValidator {

    private static final Logger log = Logger.getLogger(UserValidator.class);


    @Resource(name = "userService")
    IUserService userService;

    /**
     * Validates the user.
     * If your user is not valid method returns map of errors.
     * Else method returns null
     *
     * @param user user you want to validate
     * @return map of errors
     */
    @Override
    public Map<String, Object> validateUser(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        validateName(user, map);
        validatePassword(user, map);
        return map;
    }

    /**
     * Validates the name. Name cannot be empty
     *
     * @param user user you want to check the name
     * @param mapErrors map of errors
     */
    @Override
    public void validateName(User user, Map<String, Object> mapErrors) {
        if (user.getName().trim().isEmpty()) {
            mapErrors.put("nameFailed", "Логін пустий");
            log.info("registration : anonymous sent empty login");
        }
        if (isNameUsed(user)) {
            mapErrors.put("nameFailed", "Такий логін вже використовується");
            log.info("registration : such login (" + user.getName() + ") is used ");
        }
    }

    /**
     * Validates the password. Password cannot be empty
     *
     * @param user user you want to check the password
     * @param mapErrors map of errors
     */
    @Override
    public void validatePassword(User user, Map<String, Object> mapErrors) {
        if (user.getPassword().trim().isEmpty()) {
            mapErrors.put("passwordFailed", "Пароль пустий");
            log.info("registration : anonymous sent empty password");
        }
    }

    /**
     * Checks if such name of user is used
     *
     * @param user user you want to check the name
     * @return true if such name is used or false if such name is not used.
     */
    private boolean isNameUsed(User user) {
        List<User> allUsers = userService.getAllUsers();
        for (User compareUser : allUsers) {
            if (compareUser.getName().equals(user.getName())) {
                return true;
            }
        }
        return false;
    }
}
