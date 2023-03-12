package com.web.ManageModule.service;

import com.web.ManageModule.dao.UserDao;
import com.web.ManageModule.entity.auth_user;
import com.web.ManageModule.vo.UserQuery;
import com.web.util.random.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<auth_user> userPage(UserQuery queryCmd) {
        System.out.println("userPage");
        return userDao.userPage(queryCmd);
    }

//    @Cacheable(value = "valueName", key = "'keyName1'")
    public auth_user getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    public auth_user getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    public int addUser(auth_user user) {
        user.setUserid(UUIDUtil.generateUuid());
        user.setAddtime(new Date());
        return userDao.addUser(user);
    }

//    @CachePut(value = "valueName", key = "'keyName1'")
    public int updUser(auth_user user) {
        return userDao.updUser(user);
    }

//    @CacheEvict(value = "valueName",allEntries = true)
    public int delUser(String uids) {

        String[] uidStore = uids.split(",");
        int count = 0;
        for(String uid:uidStore){
            userDao.delUser(uid);
            count++;
        }
        if(count>0){
            return 1;
        }
        return 0;
    }

}
