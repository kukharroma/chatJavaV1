package com.chat.services.impl;

import com.chat.dao.IMessageDAO;
import com.chat.model.Message;
import com.chat.services.IMessageService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

/**
 * Represents methods for work with messages
 */
public class MessageService implements IMessageService {

    private static final Logger log = Logger.getLogger(MessageService.class);

    @Resource(name = "messageDAO")
    private IMessageDAO messageDAO;

    /**
     * @param message message you want to save
     */
    @Override
    public void save(Message message) {
        messageDAO.save(message);
    }

    /**
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * @param dateFrom time from which you want to get messages
     * @return list of messages
     */
    @Override
    public List<Message> getMessagesFromSecond(String dateFrom) {
        return messageDAO.getMessagesFromSecond(dateFrom);
    }

    /**
     * @return list of messages
     */
    @Override
    public List<Message> getLastHundredMessages() {
        return messageDAO.getLastHundredMessages();
    }

    /**
     * Deletes all messages
     */
    @Override
    public void deleteAllMessages() {
        messageDAO.deleteAllMessages();
    }

    @Override
    public void setMessageDAO(IMessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
}
