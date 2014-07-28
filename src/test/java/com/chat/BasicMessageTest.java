package com.chat;

import com.chat.model.Message;
import com.chat.model.User;
import org.bson.types.ObjectId;
import org.junit.Assert;

import java.util.Date;


public class BasicMessageTest extends Assert {

    public Message createMessage(User user, String mess, Date date) {
        Message message = new Message();
        message.setId(new ObjectId());
        message.setDate(date);
        message.setSender(user);
        message.setMessage(mess);
        return message;
    }
}
