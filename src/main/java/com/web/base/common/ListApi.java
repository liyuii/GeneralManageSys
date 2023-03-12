package com.web.base.common;

import com.web.ExcelOptModule.entity.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class ListApi {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user1 = new User("1","小李","123456789",20,1000,"清华");
        User user2 = new User("2","小张","123456789",22,1000,"北大");
        User user3 = new User("3","小赵","123456789",24,1000,"科大");
        User user4 = new User("3","小李","123456789",24,2000,"科大");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<User> arrays = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(User :: getName))), ArrayList::new));
        for (User u:arrays){
            System.out.println(u);
        }
    }
}
