package com.web.LoginModule.service;

import com.web.LoginModule.entity.Loginer;
import com.web.LoginModule.entity.tx_msg;

import java.util.List;

public interface ChatService {

    List<tx_msg> selectRecordsById(tx_msg msg);

    void addMsg(tx_msg msg);

    Loginer getByName(String name);

}
