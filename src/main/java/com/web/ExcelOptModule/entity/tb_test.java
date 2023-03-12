package com.web.ExcelOptModule.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class tb_test {


    @ExcelProperty(value = "用户ID", index = 0)
    private   String id;

    @ExcelProperty(value = "姓名", index = 1)
    private   String name;

    @ExcelProperty(value = "联系方式", index = 2)
    private   String mobile;

    @ExcelProperty(value = "邮箱", index = 3)
    private   String email;

    @ExcelProperty(value = "添加人", index = 4)
    private   String adduser;

    @ExcelProperty(value = "添加事件", index = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        return "tb_test{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", adduser='" + adduser + '\'' +
                ", addtime=" + addtime +
                '}';
    }
}
