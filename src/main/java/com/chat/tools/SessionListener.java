package com.chat.tools;

import com.chat.dao.IUserDAO;
import com.chat.dao.impl.UserDAO;
import com.chat.model.User;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.net.UnknownHostException;


public class SessionListener implements HttpSessionListener {


    private static final String DB_NAME = "chatDB";

    private IUserDAO userDAO;

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        if (getLoggedPerson() != null) {
            User user = getLoggedPerson();
            user.setOnline(false);
            try {
                userDAO = new UserDAO(new Mongo(), new Morphia(), DB_NAME);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            userDAO.save(user);
        }
    }

    private User getLoggedPerson() {
        if (SecurityContextHolder.getContext().getAuthentication() == null
                || SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().equals("anonymousUser")) {
            return null;
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User) return (User) principal;
        }
        return null;
    }
}
