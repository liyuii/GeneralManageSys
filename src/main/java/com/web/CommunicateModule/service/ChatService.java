package com.web.CommunicateModule.service;

import com.web.CommunicateModule.entity.tx_msg;
import com.web.LoginModule.entity.Loginer;

import java.util.List;

public interface ChatService {

    List<tx_msg> selectRecordsById(tx_msg msg);

    void addMsg(tx_msg msg);

    Loginer getByName(String name);

}
