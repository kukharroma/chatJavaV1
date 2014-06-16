package com.chat.model;


import com.chat.tools.TimeFormater;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Message {

    @Id
    private ObjectId id;

    private User sender;

    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    private String message;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormattedCreationTime() {
        return TimeFormater.formatDateWithSeconds(getDate());
    }
}