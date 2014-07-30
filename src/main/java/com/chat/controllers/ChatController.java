package com.chat.controllers;

import com.chat.model.Message;
import com.chat.model.User;
import com.chat.services.IMessageService;
import com.chat.services.IUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Provides methods which allow user doing
 * actions on the chat page.
 */
@Controller
public class ChatController extends BaseController {

    private static final Logger log = Logger.getLogger(ChatController.class);

    @Resource(name = "messageService")
    private IMessageService messageService;

    @Resource(name = "userService")
    private IUserService userService;

    /**
     * Calls when user moves to chat page
     * In map will be setted a username.
     * And will be transferred to chat page.
     *
     * @param model map for user param you want to transfer
     * @return chat page
     */
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat(ModelMap model) {
        User user = getLoggedPerson();
        user.setOnline(true);
        userService.save(user);
        log.info("user : " + user.getName() + " - logged in");
        model.put("name", user.getName());
        return "chat";
    }

    /**
     * Calls when user want to send a message.
     * In this method will be created a message
     * and will be sent to chat.
     *
     * @param text text of message which user want to send
     * @return created message
     */
    @RequestMapping(value = "/sendMessageToChat", method = RequestMethod.POST)
    @ResponseBody
    public Message sendMessage(@RequestParam String text) {
        if (text.trim().isEmpty()) return null;
        User user = getLoggedPerson();
        Message message = createMessage(user, text);
        messageService.save(message);
        log.info("user : " + user.getName() + " - has sent a message");
        return message;
    }

    /**
     * Creates a message with some parameters.
     * You need to set a user who wants to send
     * a message and text which  message will
     * contain.
     *
     * @param user user who wants to send a message
     * @param text text of message
     * @return created message
     */
    private Message createMessage(User user, String text) {
        Message message = new Message();
        message.setSender(user);
        message.setMessage(text);
        message.setDate(new Date());
        return message;
    }

    /**
     * Returns all users
     *
     * @return list of all users
     */
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }

    /**
     * Returns all messages
     *
     * @return list of messages
     */
    @RequestMapping(value = "/getAllMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getAllMessages() {
        List<Message> messageList = messageService.getAllMessages();
        return messageList;
    }

    /**
     * Returns list of last hundred messages
     *
     * @return list of messages
     */
    @RequestMapping(value = "/getLasHundredMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getLasHundredMessages() {
        List<Message> messageList = messageService.getLasHundredMessages();
        return messageList;
    }

    /**
     * Returns that messages which were written
     * from some time by current time
     *
     * @param dateFrom - time from which you want to get messages
     * @return list of messages
     */
    @RequestMapping(value = "/getAllMessagesByLastSecond", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getAllMessagesByLastSecond(String dateFrom) {
        List<Message> messageList = messageService.getMessagesFromSecond(dateFrom);
        return messageList;
    }
}
