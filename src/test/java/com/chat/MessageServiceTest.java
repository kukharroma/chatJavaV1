package com.chat;


import com.chat.model.Message;
import com.chat.model.User;
import com.chat.services.IMessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class MessageServiceTest extends BasicMessageTest {

    @Resource(name = "messageService")
    private IMessageService messageService;

    @Before
    @Test
    public void clearDB() {
        messageService.deleteAllMessages();
        int size = messageService.getAllMessages().size();
        assertEquals(0, size);
    }

    @Test
    public void saveMessageTest() {
        Message message = createMessage(new User(), "message", new Date());
        messageService.save(message);
        int size = messageService.getAllMessages().size();
        assertEquals(1, size);
    }

    @Test
    public void getAllMessagesTest() {
        Random random = new Random();
        int size = random.nextInt(100);
        for (int i = 0; i < size; i++) {
            Message message = createMessage(new User(), "message_" + i, new Date());
            messageService.save(message);
        }
        int actualSize = messageService.getAllMessages().size();
        assertEquals(size, actualSize);
    }

    @Test
    public void getAllMessagesByLastSecondTest() {
        messageService.save(createMessage(new User(), "message", new Date(1406494327945L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494325940L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494321985L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494327996L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494322820L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494325169L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494355685L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494326695L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494321586L)));
        messageService.save(createMessage(new User(), "message", new Date(1406494325696L)));

        List<Message> list = messageService.getAllMessagesByLastSecond(String.valueOf(new Date().getTime()));
        assertEquals(0, list.size());
        list = messageService.getAllMessagesByLastSecond(String.valueOf(1406494322820L));
        assertEquals(7, list.size());
        list = messageService.getAllMessagesByLastSecond(String.valueOf(1406494355685L));
        assertEquals(0, list.size());
        list = messageService.getAllMessagesByLastSecond(String.valueOf(1406491355685L));
        assertEquals(10, list.size());

    }

    @Test
    public void getLasHundredMessagesTest() {
        for (int i = 0; i < 200; i++) {
            messageService.save(createMessage(new User(), "message_" + i, new Date()));
        }

        List<Message> list = messageService.getLasHundredMessages();
        assertEquals(100, list.size());
        messageService.deleteAllMessages();

        for (int i = 0; i < 100; i++) {
            messageService.save(createMessage(new User(), "message_" + i, new Date()));
        }
        list = messageService.getLasHundredMessages();
        assertEquals(100, list.size());
        messageService.deleteAllMessages();

        for (int i = 0; i < 50; i++) {
            messageService.save(createMessage(new User(), "message_" + i, new Date()));
        }
        list = messageService.getLasHundredMessages();
        assertEquals(50, list.size());
        messageService.deleteAllMessages();

        for (int i = 0; i < 0; i++) {
            messageService.save(createMessage(new User(), "message_" + i, new Date()));
        }
        list = messageService.getLasHundredMessages();
        assertEquals(0, list.size());
    }

    @Test
    public void deleteAllMessagesTest() {
        Random random = new Random();
        int size = random.nextInt(100);
        for (int i = 0; i < size; i++) {
            Message message = createMessage(new User(), "message_" + i, new Date());
            messageService.save(message);
        }
        messageService.deleteAllMessages();
        int actualSize = messageService.getAllMessages().size();
        assertEquals(0, actualSize);
    }

}
