package com.chat.services.impl;

import com.chat.model.User;
import com.chat.model.authority.Authority;
import com.chat.model.authority.AuthorityBean;
import com.chat.services.IRegistrationService;
import com.chat.services.IUserService;
import com.chat.services.validator.IUserValidator;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class RegistrationService implements IRegistrationService {

    private static final Logger log = Logger.getLogger(RegistrationService.class);

    @Resource(name = "userValidator")
    private IUserValidator userValidator;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> registration(User user) {
        Map<String, Object> mapErrors = userValidator.validateUser(user);
        if (mapErrors.isEmpty()) {
            registerUser(user);
            return null;
        } else {
            return mapErrors;
        }
    }

    public void registerUser(User user) {
        setAuthority(user);
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getName()));
        userService.save(user);
        log.info(user.getName() + "has been registered");
    }

    public void setAuthority(User user) {
        Set<AuthorityBean> authorities = new HashSet();
        AuthorityBean authorityBean = new AuthorityBean();
        authorityBean.setAuthority(Authority.ROLE_USER);
        authorities.add(authorityBean);
        user.setAuthorities(authorities);
    }
}
