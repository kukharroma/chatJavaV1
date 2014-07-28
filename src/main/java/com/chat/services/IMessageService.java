package com.chat.services;


import com.chat.model.Message;

import java.util.Date;
import java.util.List;

public interface IMessageService {

    /**
     * This method saves a message
     * @param message a link to message you want to save
     */
    public void save(Message message);

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
     *
     * @return list of messages
     */
    public List<Message> getLasHundredMessages();

    /**
     * Delete all messages
     */
    public void deleteAllMessages();

}
