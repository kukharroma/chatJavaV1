package com.chat.controllers;

import com.chat.model.User;
import com.chat.services.IRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class RegistrationController {

    @Resource(name = "registrationService")
    private IRegistrationService registrationService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(ModelMap modelMap) {
        initRegisrtationParam(modelMap);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, ModelMap model) {
        Map<String, Object> map = registrationService.registration(user);
        if (map == null) {
            model.put("registered", true);
            return "registration";
        } else {
            model.put("mapErrors", map);
            return "registration";
        }
    }

    private void initRegisrtationParam(Map<String, Object> model) {
        model.put("user", new User());
        model.put("registered", false);
    }
}
