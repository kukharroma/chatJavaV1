package com.chat.dao;


import com.chat.model.Message;
import com.google.code.morphia.dao.DAO;
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.Date;
import java.util.List;

public interface IMessageDAO extends DAO<Message, ObjectId> {

    /**
     *
     * @return list of messages
     */
    public List<Message> getLasHundredMessages();

    /**
     *
     * @return list of messages
     */
    public List<Message> getAllMessages();

    /**
     *
     * @param dateFrom time from which you want to get messages
     * @return list of messages
     */
    public List<Message> getMessagesFromSecond(String dateFrom);

    /**
     * Delete all messages
     */
    public void deleteAllMessages();
}
