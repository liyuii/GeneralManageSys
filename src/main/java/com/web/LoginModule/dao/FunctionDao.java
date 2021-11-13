package com.web.LoginModule.dao;

import com.web.LoginModule.entity.Function;

import java.util.List;

public interface FunctionDao {

    List<Function> funPage(String id);

    Function getFunByName(String name);

}
