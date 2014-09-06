package com.chat.mockTest;


import com.chat.dao.IMessageDAO;
import com.chat.model.Message;
import com.chat.services.IMessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class MessageServiceMockTest {

    @Resource(name = "messageService")
    private IMessageService messageService;

    private IMessageDAO messageDAOMock;

    /**
     * Creates mock object (messageDAOMock)
     */
    @Before
    public void setUp(){
        messageDAOMock = createMock(IMessageDAO.class);
        messageService.setMessageDAO(messageDAOMock);
    }

    @Test
    public void testGetAllMessages(){
        expect(messageDAOMock.getAllMessages()).andReturn(new ArrayList<Message>());
        replay(messageDAOMock);
        messageService.getAllMessages();
        verify(messageDAOMock);
    }


    @Test
    public void testGetMessagesFromSecond(){
        String date = "date";
        expect(messageDAOMock.getMessagesFromSecond(date)).andReturn(new ArrayList<Message>());
        replay(messageDAOMock);
        messageService.getMessagesFromSecond(date);
        verify(messageDAOMock);
    }

    @Test
    public void testGetLastHundredMessages(){
        expect(messageDAOMock.getLastHundredMessages()).andReturn(new ArrayList<Message>());
        replay(messageDAOMock);
        messageService.getLastHundredMessages();
        verify(messageDAOMock);
    }

    @Test
    public void testDeleteAllMessages(){
        messageDAOMock.deleteAllMessages();
        replay(messageDAOMock);
        messageService.deleteAllMessages();
        verify(messageDAOMock);
    }

}
