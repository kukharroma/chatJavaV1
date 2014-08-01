package com.chat.model.authority;


import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * Holds one role (one Authority)
 */
public class AuthorityBean implements Serializable{
    /**
     * id of AuthorityBean
     */
    @Id
    private ObjectId id;

    /**
     * instance of enum Authority
     */
    private Authority authority;

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
