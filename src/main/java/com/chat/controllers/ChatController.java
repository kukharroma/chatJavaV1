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

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat(ModelMap model) {
        User user = getLoggedPerson();
        user.setOnline(true);
        userService.save(user);
        log.info(user.getName() + " logged in");
        model.put("name", user.getName());
        return "chat";
    }

    @RequestMapping(value = "/sendMessageToChat", method = RequestMethod.POST)
    @ResponseBody
    public Message sendMessage(@RequestParam String text) {
        if (text.trim().isEmpty()) return null;
        User user = getLoggedPerson();
        Message message = createMessage(user, text);
        messageService.save(message);
        log.info(user.getName() + " has sent a message");
        return message;
    }

    private Message createMessage(User user, String text) {
        Message message = new Message();
        message.setSender(user);
        message.setMessage(text);
        message.setDate(new Date());
        return message;
    }


    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }

    @RequestMapping(value = "/getAllMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getAllMessages() {
        List<Message> messageList = messageService.getAllMessages();
        return messageList;
    }

    @RequestMapping(value = "/getLasHundredMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getLasHundredMessages() {
        List<Message> messageList = messageService.getLasHundredMessages();
        return messageList;
    }

    @RequestMapping(value = "/getAllMessagesByLastSecond", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getAllMessagesByLastSecond(String dateFrom) {
        List<Message> messageList = messageService.getAllMessagesByLastSecond(dateFrom);
        return messageList;
    }
}
