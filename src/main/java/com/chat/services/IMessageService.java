package com.chat.services;


import com.chat.model.Message;

import java.util.Date;
import java.util.List;

public interface IMessageService {

    public void save(Message message);

    public List<Message> getAllMessages();

    public List<Message> getAllMessagesByLastSecond(String dateFrom);

    public List<Message> getLasHundredMessages();

    public void deleteAllMessages();

}
