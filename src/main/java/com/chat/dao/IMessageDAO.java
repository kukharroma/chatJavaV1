package com.chat.dao;


import com.chat.model.Message;
import com.google.code.morphia.dao.DAO;
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.Date;
import java.util.List;

/**
 *You can implement this interface if you want to
 * declare method which will work with users
 */
public interface IMessageDAO extends DAO<Message, ObjectId> {

    /**
     * Returns last hundred messages from database
     *
     * @return list of messages
     */
    public List<Message> getLasHundredMessages();

    /**
     *  Returns all messages from database
     *
     * @return list of messages
     */
    public List<Message> getAllMessages();

    /**
     * Returns that messages which were written from some time by current time
     *
     * @param dateFrom time from which you want to get messages
     * @return list of messages
     */
    public List<Message> getMessagesFromSecond(String dateFrom);

    /**
     * Delete all messages from database
     */
    public void deleteAllMessages();
}
