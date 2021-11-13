package com.web.LoginModule.dao;

import com.web.LoginModule.entity.Loginer;

import java.util.List;

public interface LoginerDao {

    List<Loginer> loginPage(String id);

    Loginer getLoginByName(String name);

}
