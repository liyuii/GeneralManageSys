package com.web.serviceImpl;

import com.web.dao.ChatDao;
import com.web.dao.LoginerDao;
import com.web.entity.Loginer;
import com.web.entity.tx_msg;
import com.web.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatDao chatDao;

    @Autowired
    LoginerDao loginerDao;

    @Override
    public List<tx_msg> selectRecordsById(tx_msg msg) {
        return chatDao.selectRecordsById(msg);
    }

    @Override
    public void addMsg(tx_msg msg) {
        chatDao.addMsg(msg);
    }

    @Override
    public Loginer getByName(String name) {
        return loginerDao.getLoginByName(name);
    }
}
