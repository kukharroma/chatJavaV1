package com.chat.controllers;

import com.chat.model.User;
import com.chat.services.IRegistrationService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Provides methods for user registration.
 * Consists of methods which prepare parameters
 * for registration. Methods which move to registration page
 * and method that performs a major role - register a user.
 */
@Controller
public class RegistrationController {

    private static final Logger log = Logger.getLogger(RegistrationController.class);

    @Resource(name = "registrationService")
    private IRegistrationService registrationService;

    /**
     * Calls when user wants to register
     * and moves to registration page
     * Reg. page contains a registration form.
     * In map you need to set initialization parameters
     *
     * @param modelMap map which consists init params
     * @return registration page.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(ModelMap modelMap) {
        initRegisrtationParam(modelMap);
        return "registration";
    }

    /**
     * Registers a user. For registration you need
     * a user who will be registered and map in which
     * will be setted a result of registration. Map will
     * be transferred to registration page.
     *
     * @param user  user you want to register
     * @param model result map of registration
     * @return registration page.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, ModelMap model) {
        Map<String, Object> map = registrationService.registration(user);
        if (map == null) {
            model.put("registered", true);
            model.put("user", new User());
            return "registration";
        } else {
            model.put("mapErrors", map);
            return "registration";
        }
    }

    /**
     * Initialization parameters which are needed for registration.
     * In map will be setted new user and boolean variable which
     * shows that user isn't registered
     *
     * @param model map for initialization parameters you need to set
     */
    private void initRegisrtationParam(Map<String, Object> model) {
        model.put("user", new User());
        model.put("registered", false);
    }
}
