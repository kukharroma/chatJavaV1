package com.chat.model;


import com.chat.tools.TimeFormater;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 *
 */
public class Message {

    /**
     * id of message
     */
    @Id
    private ObjectId id;

    /**
     * user who created this message
     */
    private User sender;

    /**
     * date of creating the message
     */
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private Date date;

    /**
     * text of message
     */
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
