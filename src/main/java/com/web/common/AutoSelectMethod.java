package com.web.common;

import java.util.HashMap;
import java.util.Map;

public class AutoSelectMethod {

    //根据传入的方法名和参数选择相应的方法执行

    public Map<String,Object> method1(String arg1){
        Map<String,Object> map = new HashMap<>();
        map.put("arg1",arg1);
        return map;
    }

    public Map<String,Object> method2(String arg1,String arg2){
        Map<String,Object> map = new HashMap<>();
        map.put("arg1",arg1);
        map.put("arg2",arg2);
        return map;
    }

    public Map<String,Object> method3(String arg1,String arg2,String arg3){
        Map<String,Object> map = new HashMap<>();
        map.put("arg1",arg1);
        map.put("arg2",arg2);
        map.put("arg3",arg3);
        return map;
    }

    public Map<String,Object> method4(String arg1,String arg2){
        Map<String,Object> map = new HashMap<>();
        map.put("arg1",arg1);
        map.put("arg2",arg2);
        return map;
    }

    public Map<String,Object> method5(String arg1){
        Map<String,Object> map = new HashMap<>();
        map.put("arg1",arg1);
        return map;
    }

    public Map<String,Object> method6(String arg1,String arg2){
        Map<String,Object> map = new HashMap<>();
        map.put("arg1",arg1);
        map.put("arg2",arg2);
        return map;
    }


}
