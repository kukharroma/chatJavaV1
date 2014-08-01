package com.chat;


import com.chat.model.Message;
import com.chat.model.User;
import com.chat.services.impl.MessageService;
import com.chat.services.impl.UserService;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class MessageServiceTest extends Assert {

    @Resource(name = "messageService")
    private MessageService messageService;

    @Resource(name = "userService")
    private UserService userService;

    @Before
    @After
    public void clearDB() {
        messageService.deleteAllMessages();
        userService.deleteAllUsers();
    }

    @Test
    public void testMessageSave() {
        User user = createUser("name", "password", false);
        Message message = createMessage(user, "text", new Date());
        List<Message> list = messageService.getAllMessages();
        assertTrue(list.isEmpty());
        messageService.save(message);
        userService.save(user);
        list = messageService.getAllMessages();
        assertTrue(!list.isEmpty());
        assertTrue(equalsMessages(message, list.get(0)));
    }

    @Test(expected = NullPointerException.class)
    public void testMessageSaveWithEmptyMessage() {
        User user = createUser("name", "password", false);
        Message message = createMessage(user, null, new Date());
        List<Message> list = messageService.getAllMessages();
        assertTrue(list.isEmpty());
        messageService.save(message);
        userService.save(user);
        list = messageService.getAllMessages();
        assertTrue(!list.isEmpty());
        assertTrue(equalsMessages(message, list.get(0)));
    }

    @Test
    public void testGetAllMessages() {
        User user = createUser("name", "password", false);
        Message message1 = createMessage(user, "message1", new Date());
        Message message2 = createMessage(user, "message2", new Date());
        Message message3 = createMessage(user, "message3", new Date());
        Message message4 = createMessage(user, "message4", new Date());
        Message message5 = createMessage(user, "message5", new Date());
        Message message6 = createMessage(user, "message6", new Date());
        messageService.save(message1);
        messageService.save(message2);
        messageService.save(message3);
        messageService.save(message4);
        messageService.save(message5);
        messageService.save(message6);

        List<Message> list = messageService.getAllMessages();

        assertTrue(equalsMessages(message1, list.get(0)));
        assertTrue(equalsMessages(message2, list.get(1)));
        assertTrue(equalsMessages(message3, list.get(2)));
        assertTrue(equalsMessages(message4, list.get(3)));
        assertTrue(equalsMessages(message5, list.get(4)));
        assertTrue(equalsMessages(message6, list.get(5)));

    }

    @Test
    public void testGetLastHundredMessages() {
        for (int k = 0; k < 125; k++) {
            Message message = createMessage(new User(), "mess" + k, new Date());
            messageService.save(message);
        }
        List<Message> list = messageService.getLasHundredMessages();
        assertEquals(100, list.size());
        assertTrue(list.get(0).getMessage().equals("mess25"));
        assertTrue(list.get(99).getMessage().equals("mess124"));
    }

    @Test
    public void testDeleteAllMessages() {
        User user = createUser("name", "password", false);
        Message message1 = createMessage(user, "message1", new Date());
        Message message2 = createMessage(user, "message2", new Date());
        Message message3 = createMessage(user, "message3", new Date());
        messageService.save(message1);
        messageService.save(message2);
        messageService.save(message3);

        List<Message> list = messageService.getAllMessages();
        assertTrue(!list.isEmpty());
        messageService.deleteAllMessages();
        list = messageService.getAllMessages();
        assertTrue(list.isEmpty());
    }


    public boolean equalsMessages(Message first, Message second) {
        boolean result = true;
        result = result && first.getId().equals(second.getId());
        result = result && first.getDate().equals(second.getDate());
        result = result && first.getMessage().equals(second.getMessage());
        result = result && equalsUsers(first.getSender(), second.getSender());
        return result;
    }

    private Message createMessage(User user, String mess, Date date) {
        Message message = new Message();
        message.setId(new ObjectId());
        message.setDate(date);
        message.setSender(user);
        message.setMessage(mess);
        return message;
    }

    private User createUser(String name, String password, Boolean online) {
        User user = new User();
        user.setId(new ObjectId());
        user.setName(name);
        user.setPassword(password);
        user.setOnline(online);
        return user;
    }

    private boolean equalsUsers(User firstUser, User secondUser) {
        boolean result = true;
        result = result && firstUser.getId().equals(secondUser.getId());
        result = result && firstUser.getUsername().equals(secondUser.getUsername());
        result = result && firstUser.getPassword().equals(secondUser.getPassword());
        result = result && firstUser.getName().equals(secondUser.getName());
        return result;
    }
}
