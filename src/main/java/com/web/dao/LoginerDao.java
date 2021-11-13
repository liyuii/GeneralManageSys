package com.web.dao;

import com.web.entity.Loginer;

import java.util.List;

public interface LoginerDao {

    List<Loginer> loginPage(String id);

    Loginer getLoginByName(String name);

}
