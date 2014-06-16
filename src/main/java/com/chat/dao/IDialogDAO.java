package com.chat.dao;

import com.chat.model.Dialog;
import com.google.code.morphia.dao.DAO;
import org.bson.types.ObjectId;


public interface IDialogDAO extends DAO<Dialog, ObjectId> {
}
