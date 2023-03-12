package com.web.ManageModule.dao;

import com.web.ManageModule.entity.Loginer;

import java.util.List;

public interface LoginerDao {

    List<Loginer> loginPage(String id);

    Loginer getLoginByName(String name);

}
