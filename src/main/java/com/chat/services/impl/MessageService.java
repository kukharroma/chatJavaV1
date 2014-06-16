package com.chat.services.impl;

import com.chat.dao.IMessageDAO;
import com.chat.model.Message;
import com.chat.services.IMessageService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


public class MessageService implements IMessageService {

    @Resource(name = "messageDAO")
    private IMessageDAO massageDAO;

    @Override
    public void save(Message message) {
        massageDAO.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return massageDAO.getAllMessages();
    }

    @Override
    public List<Message> getAllMessagesByLastSecond(String dateFrom) {
        return massageDAO.getMessagesByLastSecond(dateFrom);
    }

    @Override
    public List<Message> getLasHundredMessages() {
        return massageDAO.getLasHundredMessages();
    }
}
