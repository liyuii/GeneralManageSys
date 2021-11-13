package com.web.service;

import com.web.entity.Loginer;
import com.web.entity.tx_msg;

import java.util.List;

public interface ChatService {

    List<tx_msg> selectRecordsById(tx_msg msg);

    void addMsg(tx_msg msg);

    Loginer getByName(String name);

}
