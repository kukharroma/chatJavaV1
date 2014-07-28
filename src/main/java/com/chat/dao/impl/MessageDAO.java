package com.chat.dao.impl;


import com.chat.dao.IMessageDAO;
import com.chat.model.Message;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.sun.corba.se.spi.ior.ObjectId;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class MessageDAO extends BasicDAO<Message, ObjectId> implements IMessageDAO {

    private static final Logger log = Logger.getLogger(MessageDAO.class);

    protected MessageDAO(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }

    @Override
    public List<Message> getLasHundredMessages() {
        List<Message> list = getDatastore().createQuery(Message.class).asList();
        if (list.size() < 100) {
            return list;
        } else {
            return list.subList((list.size() - 100), list.size());
        }
    }

    @Override
    public List<Message> getAllMessages() {
        return getDatastore().createQuery(Message.class).asList();
    }

    @Override
    public List<Message> getMessagesByLastSecond(String dateFrom) {
        long longDate = Long.valueOf(dateFrom);
        Date date = new Date(longDate);
        return getDatastore().createQuery(Message.class).field("date").greaterThan(date).asList();
    }

    @Override
    public void deleteAllMessages() {
        deleteByQuery(getDatastore().createQuery(Message.class));
    }
}
