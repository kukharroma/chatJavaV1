package com.chat.controllers;


import com.chat.dao.IUserDAO;
import com.chat.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class WelcomeController extends BaseController {

    @RequestMapping("")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/welcome")
    public String welcomePage() {
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginFailed(ModelMap model) {
        model.put("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "welcome";
    }


}
