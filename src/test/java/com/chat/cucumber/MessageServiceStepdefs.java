package com.chat.cucumber;


import com.chat.model.Message;
import com.chat.model.User;
import com.chat.services.IMessageService;
import com.chat.services.IUserService;
import cucumber.api.java.en.Given;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.java.StepDefAnnotation;
import org.bson.types.ObjectId;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class MessageServiceStepdefs {

    @Resource(name = "messageService")
    private IMessageService messageService;

    @Resource(name = "userService")
    private IUserService userService;

    @Given("^save a message in db with text \"([^\"]*)\"$")
    public void save_a_message_in_db_with_text(String text) {
        User user = createUser("user", "password", false);
        Message message = createMessage(user, text, new Date());
        userService.save(user);
        messageService.save(message);
    }

    @Given("^get all messages$")
    public void get_all_messages() throws Throwable {
        messageService.deleteAllMessages();
        Message message1 = createMessage(null, "text", new Date());
        Message message2 = createMessage(null, "text", new Date());
        Message message3 = createMessage(null, "text", new Date());
        Message message4 = createMessage(null, "text", new Date());
        messageService.save(message1);
        messageService.save(message2);
        messageService.save(message3);
        messageService.save(message4);
        List<Message> list = messageService.getAllMessages();
        assertEquals(list.size(), 4);
    }

    @Given("^get last hundred messages$")
    public void get_last_hundred_messages() {
        messageService.deleteAllMessages();
        for (int k = 0; k < 125; k++) {
            Message message = createMessage(new User(), "mess" + k, new Date());
            messageService.save(message);
        }
        List<Message> list = messageService.getLastHundredMessages();
        assertEquals(100, list.size());
        assertTrue(list.get(0).getMessage().equals("mess25"));
        assertTrue(list.get(99).getMessage().equals("mess124"));
    }

    @Given("^delete all messages$")
    public void delete_all_messages() {
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

    /**
     * Equals two instance of Message. If objects are equal
     * returns true else returns false.
     *
     * @param first  message you want to compare
     * @param second message with whom you want to compare
     * @return If objects are equal returns true else returns false.
     */
    public boolean equalsMessages(Message first, Message second) {
        boolean result = true;
        result = result && first.getId().equals(second.getId());
        result = result && first.getDate().equals(second.getDate());
        result = result && first.getMessage().equals(second.getMessage());
        result = result && equalsUsers(first.getSender(), second.getSender());
        return result;
    }

    /**
     * Creates an instance of Message
     *
     * @param user user who created a message
     * @param mess text of message
     * @param date date when message was created
     * @return instance of message
     */
    private Message createMessage(User user, String mess, Date date) {
        Message message = new Message();
        message.setId(new ObjectId());
        message.setDate(date);
        message.setSender(user);
        message.setMessage(mess);
        return message;
    }

    /**
     * Creates an instance of User
     *
     * @param name     name of user
     * @param password user's password
     * @param online   true if user is online, false if user is offline.
     * @return created instance of User
     */
    private User createUser(String name, String password, Boolean online) {
        User user = new User();
        user.setId(new ObjectId());
        user.setName(name);
        user.setPassword(password);
        user.setOnline(online);
        return user;
    }

    /**
     * Equals two instance of User. If objects are equal
     * returns true else returns false.
     *
     * @param firstUser  user you want to compare
     * @param secondUser user with whom you want to compare
     * @return If objects are equal returns true else returns false.
     */
    private boolean equalsUsers(User firstUser, User secondUser) {
        boolean result = true;
        result = result && firstUser.getId().equals(secondUser.getId());
        result = result && firstUser.getUsername().equals(secondUser.getUsername());
        result = result && firstUser.getPassword().equals(secondUser.getPassword());
        result = result && firstUser.getName().equals(secondUser.getName());
        return result;
    }
}
