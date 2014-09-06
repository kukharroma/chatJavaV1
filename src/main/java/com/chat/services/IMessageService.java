package com.chat.services;


import com.chat.dao.IMessageDAO;
import com.chat.model.Message;

import java.util.List;

/**
 * Represents methods which you can implements if
 * you want to work with messages
 */
public interface IMessageService {

    /**
     * Saves a message
     *
     * @param message message you want to save
     */
    public void save(Message message);

    /**
     * Returns all messages
     *
     * @return list of messages
     */
    public List<Message> getAllMessages();

    /**
     * Returns that messages which were written from some time
     * by current time
     *
     * @param dateFrom time from which you want to get messages
     * @return list of messages
     */
    public List<Message> getMessagesFromSecond(String dateFrom);

    /**
     * Returns last hundred messages from database
     *
     * @return list of messages
     */
    public List<Message> getLastHundredMessages();

    /**
     * Deletes all messages
     */
    public void deleteAllMessages();

    /**
     * Sets a DAO for messageService
     * @param messageDAO dao you want to set for messageService
     */
    public void setMessageDAO(IMessageDAO messageDAO);

}
