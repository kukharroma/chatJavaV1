package com.chat.services.impl;

import com.chat.dao.IDialogDAO;
import com.chat.model.Dialog;
import com.chat.services.IDialogService;

import javax.annotation.Resource;


public class DialogService implements IDialogService {

    @Resource(name = "dialogDAO")
    private IDialogDAO dialogDAO;

    public void save(Dialog dialog) {
        dialogDAO.save(dialog);
    }
}
