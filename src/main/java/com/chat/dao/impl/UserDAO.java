package com.chat.dao.impl;


import com.chat.dao.IUserDAO;
import com.chat.model.User;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.sun.corba.se.spi.ior.ObjectId;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDAO extends BasicDAO<User, ObjectId> implements IUserDAO {

    private static final Logger log = Logger.getLogger(UserDAO.class);

    public UserDAO(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }

    @Override
    public User loadUserByUsername(String name) {
        return getDatastore().createQuery(User.class).filter("name", name).get();
    }

    @Override
    public List<User> getAllOnlineUser() {
        return getDatastore().createQuery(User.class).filter("online", Boolean.TRUE).asList();
    }

    @Override
    public List<User> getAllUsers() {
        return getDatastore().createQuery(User.class).asList();
    }

    @Override
    public void deleteAllUsers() {
        deleteByQuery(getDatastore().createQuery(User.class));
    }
}
