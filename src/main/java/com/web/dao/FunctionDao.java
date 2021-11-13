package com.web.dao;

import com.web.entity.Function;
import com.web.entity.Role;

import java.util.List;

public interface FunctionDao {

    List<Function> funPage(String id);

    Function getFunByName(String name);

}
