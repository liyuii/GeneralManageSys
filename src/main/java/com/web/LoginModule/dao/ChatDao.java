package com.web.LoginModule.dao;

import com.web.LoginModule.entity.tx_msg;

import java.util.List;

public interface ChatDao {

    List<tx_msg> selectRecordsById(tx_msg msg);

    void addMsg(tx_msg msg);

}
