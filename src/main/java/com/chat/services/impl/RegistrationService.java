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

/**
 * Represents methods with which you can register users
 *
 */
public class RegistrationService implements IRegistrationService {

    private static final Logger log = Logger.getLogger(RegistrationService.class);

    @Resource(name = "userValidator")
    private IUserValidator userValidator;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "passwordEncoder")
    private Md5PasswordEncoder passwordEncoder;

    /**
     * Returns a map of errors if your user is not valid. Else this method returns null
     *
     * @param user user you want to validate
     * @return map of errors or null
     */
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

    /**
     * Registers a user in database, encode password and set roles.
     *
     * @param user user you want to register
     */
    public void registerUser(User user) {
        setAuthority(user);
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getName()));
        userService.save(user);
        log.info("user : " + user.getName() + " - has been registered");
    }

    /**
     * Sets roles to the user
     *
     * @param user user you want set Authority
     */
    public void setAuthority(User user) {
        Set<AuthorityBean> authorities = new HashSet();
        AuthorityBean authorityBean = new AuthorityBean();
        authorityBean.setAuthority(Authority.ROLE_USER);
        authorities.add(authorityBean);
        user.setAuthorities(authorities);
    }
}
