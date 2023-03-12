package com.web.ManageModule.dao;

import com.web.ManageModule.entity.Function;

import java.util.List;

public interface FunctionDao {

    List<Function> funPage(String id);

    Function getFunByName(String name);

}
