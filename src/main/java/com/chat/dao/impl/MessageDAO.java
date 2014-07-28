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

    /**
     * Create a MessageDAO that represents method which allows access to messages in database
     * @param mongo instance of Mongo
     * @param morphia  instance of Morphia
     * @param dbName name of database
     */
    protected MessageDAO(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }

    /**
     * This method returns last hundred messages from database
     *
     * @return - a list of messages
     */
    @Override
    public List<Message> getLasHundredMessages() {
        List<Message> list = getDatastore().createQuery(Message.class).asList();
        if (list.size() < 100) {
            return list;
        } else {
            return list.subList((list.size() - 100), list.size());
        }
    }

    /**
     * This method returns all messages from database
     *
     * @return - a list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        return getDatastore().createQuery(Message.class).asList();
    }

    /**
     * This method returns that messages which were written from some time by current time
     *
     * @param dateFrom - time from which you want to get messages
     * @return - a list of messages
     */
    @Override
    public List<Message> getMessagesFromSecond(String dateFrom) {
        long longDate = Long.valueOf(dateFrom);
        Date date = new Date(longDate);
        return getDatastore().createQuery(Message.class).field("date").greaterThan(date).asList();
    }

    /**
     * Delete all messages from database
     */
    @Override
    public void deleteAllMessages() {
        deleteByQuery(getDatastore().createQuery(Message.class));
    }
}
