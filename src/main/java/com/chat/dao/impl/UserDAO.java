package com.chat.dao.impl;


import com.chat.dao.IUserDAO;
import com.chat.model.User;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.sun.corba.se.spi.ior.ObjectId;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Provides methods that allow you to interact with users
 * Has one constructor which consists of three objects (instance
 * of Mongo, of Morphia and name of database in  String)
 */
public class UserDAO extends BasicDAO<User, ObjectId> implements IUserDAO {

    private static final Logger log = Logger.getLogger(UserDAO.class);

    /**
     * Constructor of UserDAO needs three params (instance
     * of Mongo, of Morphia and name of database in String)
     *
     * @param mongo instance of Mongo
     * @param morphia  instance of Morphia
     * @param dbName name of database
     */
    public UserDAO(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }

    /**
     * Returns a user by name
     * @param name name of user you want to load
     * @return instance of User
     */
    @Override
    public User loadUserByUsername(String name) {
        return getDatastore().createQuery(User.class).filter("name", name).get();
    }

    /**
     * Returns all users which are online (online is true)
     * @return list of users
     */
    @Override
    public List<User> getAllOnlineUser() {
        return getDatastore().createQuery(User.class).filter("online", Boolean.TRUE).asList();
    }

    /**
     * Returns all users
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        return getDatastore().createQuery(User.class).asList();
    }

    /**
     * Delete all users
     */
    @Override
    public void deleteAllUsers() {
        deleteByQuery(getDatastore().createQuery(User.class));
    }
}
