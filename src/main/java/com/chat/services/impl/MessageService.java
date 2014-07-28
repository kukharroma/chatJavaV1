package com.chat.services.impl;

import com.chat.dao.IMessageDAO;
import com.chat.model.Message;
import com.chat.services.IMessageService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;


public class MessageService implements IMessageService {

    private static final Logger log = Logger.getLogger(MessageService.class);

    @Resource(name = "messageDAO")
    private IMessageDAO massageDAO;

    /**
     * This method saves a message
     * @param message a link to message you want to save
     */
    @Override
    public void save(Message message) {
        massageDAO.save(message);
    }

    /**
     * This method returns all messages
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        return massageDAO.getAllMessages();
    }

    /**
     * This method returns that messages which were written from some time by current time
     * @param dateFrom time from which you want to get messages
     * @return list of messages
     */
    @Override
    public List<Message> getMessagesFromSecond(String dateFrom) {
        return massageDAO.getMessagesFromSecond(dateFrom);
    }

    /**
     * This method returns last hundred messages from database
     * @return list of messages
     */
    @Override
    public List<Message> getLasHundredMessages() {
        return massageDAO.getLasHundredMessages();
    }

    /**
     * Delete all messages from database
     */
    @Override
    public void deleteAllMessages() {
        massageDAO.deleteAllMessages();
    }
}
