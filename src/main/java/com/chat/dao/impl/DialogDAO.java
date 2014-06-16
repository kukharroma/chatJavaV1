package com.chat.dao.impl;

import com.chat.dao.IDialogDAO;
import com.chat.model.Dialog;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;

public class DialogDAO extends BasicDAO<Dialog, ObjectId> implements IDialogDAO {

    protected DialogDAO(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }
}
