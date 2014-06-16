package com.chat.services.validator;


import com.chat.model.User;

import java.util.Map;

public interface IUserValidator {

    public Map<String, Object> validateUser(User user);

    public void validateName(User user, Map<String, Object> mapErrors);

    public void validatePassword(User user, Map<String, Object> mapErrors);
}
