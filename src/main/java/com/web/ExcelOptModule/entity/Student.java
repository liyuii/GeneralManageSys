package com.web.ExcelOptModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    /**
     * id
     */
    private String id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生性别
     */
    private int sex;

    private Date birthday;

    private Date registrationDate;

    public Student(String name,int sex){
        this.name = name;
        this.sex = sex;
    }
}
