package com.chat.tools;

import com.chat.controllers.BaseController;
import com.chat.dao.IDBConfiguration;
import com.chat.dao.IUserDAO;
import com.chat.dao.impl.UserDAO;
import com.chat.model.User;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.net.UnknownHostException;

/**
 * Represents method  which will be notified when user's session
 * created or destroyed
 */
public class SessionListener extends BaseController implements HttpSessionListener, IDBConfiguration {

    private IUserDAO userDAO;

    private static final Logger log = Logger.getLogger(SessionListener.class);

    /**
     * Notification that a session was created.
     *
     * @param sessionEvent the notification event
     */
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    }

    /**
     * Notification that a session is about to be invalidated.
     *
     * @param sessionEvent the notification event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        if (getLoggedPerson() != null) {
            User user = getLoggedPerson();
            user.setOnline(false);
            try {
                userDAO = new UserDAO(new Mongo(), new Morphia(), DB_NAME);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                log.error("cannot create userDAO, " + "dbName = " + DB_NAME);
            }
            userDAO.save(user);
            log.info("user : " + user.getName() + " - logout");
        }
    }

    /**
     * Returns the user which is logged
     * @return instance of user
     */
    protected User getLoggedPerson() {
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
