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

@Controller
public class ChatController extends BaseController {

    private static final Logger log = Logger.getLogger(ChatController.class);

    @Resource(name = "messageService")
    private IMessageService messageService;

    @Resource(name = "userService")
    private IUserService userService;

    /**
     * This method calls when user step to chat page
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
     * This method calls when user want to send a message
     *
     * @param text test of message which user want to send
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
     * This method returns a message
     *
     * @param user link to user who wants to send a message
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
     * This method returns all users
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
     * This method returns all messages
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
     * This method returns list of last hundred messages
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
     * This method returns that messages which were written from some time by current time
     *
     * @param dateFrom - time from which you want to get messages
     * @return - a list of messages
     */
    @RequestMapping(value = "/getAllMessagesByLastSecond", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getAllMessagesByLastSecond(String dateFrom) {
        List<Message> messageList = messageService.getMessagesFromSecond(dateFrom);
        return messageList;
    }
}
