package com.chat.dao;


import com.chat.model.Message;
import com.google.code.morphia.dao.DAO;
import com.sun.corba.se.spi.ior.ObjectId;

import java.util.Date;
import java.util.List;

public interface IMessageDAO extends DAO<Message, ObjectId> {

    public List<Message> getLasHundredMessages();

    public List<Message> getAllMessages();

    public List<Message> getMessagesByLastSecond(String dateFrom);

    public void deleteAllMessages();
}
