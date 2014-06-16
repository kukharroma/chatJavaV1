package com.chat.model;


import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.List;

public class Dialog {

    @Id
    private ObjectId id;

    private List<Message> listOfMessages;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Message> getListOfMessages() {
        return listOfMessages;
    }

    public void setListOfMessages(List<Message> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }
}
