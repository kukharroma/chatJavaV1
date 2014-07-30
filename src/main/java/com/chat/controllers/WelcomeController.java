package com.chat.controllers;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Provides methods which calls for moving
 * to welcome, login , logout pages.
 * Pages store in package webapp-> WEB-INF.
 * Definition name of pages stores in file tiles.xml
 * resources->tiles->tiles.xml
 */
@Controller
public class WelcomeController extends BaseController {

    private static final Logger log = Logger.getLogger(WelcomeController.class);

    /**
     * Calls when application context is empty and
     * move to welcome page. Also calls when the
     * project was first launched.
     *
     * @return welcome page
     */
    @RequestMapping("")
    public String welcome() {
        return "welcome";
    }

    /**
     * Calls when user moves to welcome page also
     * when application context is "welcome".
     * Moves to the welcome page.
     *
     * @return welcome page
     */
    @RequestMapping(value = "/welcome")
    public String welcomePage() {
        return "welcome";
    }

    /**
     * Calls when user wants to login and moves
     * to login page with login form. Calls when
     * app context is "login".
     *
     * @return login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * Calls when something went wrong during
     * user login. And moves to the login page
     * with ModelMap which contains errors.
     *
     * @param model contains errors of loginFailed
     * @return login page
     */
    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginFailed(ModelMap model) {
        model.put("error", "true");
        return "login";
    }

    /**
     * Calls when user wants to logout. If logout is
     * successful moves to welcome page.
     *
     * @return welcome page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "welcome";
    }


}
