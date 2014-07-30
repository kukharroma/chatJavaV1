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

/**
 * Provides methods that allow you to interact with messaging
 * Has one constructor which consists of three objects (instance
 * of Mongo, of Morphia and name of database in  String )
 *
 * @see com.google.code.morphia.dao.BasicDAO
 * @see com.chat.dao.IMessageDAO
 */
public class MessageDAO extends BasicDAO<Message, ObjectId> implements IMessageDAO {

    private static final Logger log = Logger.getLogger(MessageDAO.class);

    /**
     * Constructor of MessageDAO.
     *
     * @param mongo   instance of Mongo
     * @param morphia instance of Morphia
     * @param dbName  name of database
     */
    protected MessageDAO(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }

    /**
     *
     * @return list of messages
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
     *
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        return getDatastore().createQuery(Message.class).asList();
    }

    /**
     *
     * @param dateFrom - time from which you want to get messages
     * @return list of messages
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
