package com.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {

    private String userId;
    private String userLocal;
    private String userName;
    private String userPyName;
    private String userPhone;
    private String userType;
    private BigDecimal userNumber;
    private BigDecimal userRuler;
    private BigDecimal userMoney;
    private BigDecimal userMoney2021;
    private BigDecimal userMoney2022;
    private BigDecimal userMoney2023;
    private BigDecimal userMoney2024;
    private BigDecimal userMoney2025;
    private BigDecimal userPay;
    private BigDecimal userSurplus;
    private int userAccumulate;
    private Date createTime;
    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLocal() {
        return userLocal;
    }

    public void setUserLocal(String userLocal) {
        this.userLocal = userLocal;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(BigDecimal userNumber) {
        this.userNumber = userNumber;
    }

    public BigDecimal getUserRuler() {
        return userRuler;
    }

    public void setUserRuler(BigDecimal userRuler) {
        this.userRuler = userRuler;
    }

    public BigDecimal getUserPay() {
        return userPay;
    }

    public void setUserPay(BigDecimal userPay) {
        this.userPay = userPay;
    }

    public BigDecimal getUserSurplus() {
        return userSurplus;
    }

    public void setUserSurplus(BigDecimal userSurplus) {
        this.userSurplus = userSurplus;
    }

    public BigDecimal getUserMoney2021() {
        return userMoney2021;
    }

    public void setUserMoney2021(BigDecimal userMoney2021) {
        this.userMoney2021 = userMoney2021;
    }

    public BigDecimal getUserMoney2022() {
        return userMoney2022;
    }

    public void setUserMoney2022(BigDecimal userMoney2022) {
        this.userMoney2022 = userMoney2022;
    }

    public BigDecimal getUserMoney2023() {
        return userMoney2023;
    }

    public void setUserMoney2023(BigDecimal userMoney2023) {
        this.userMoney2023 = userMoney2023;
    }

    public BigDecimal getUserMoney2024() {
        return userMoney2024;
    }

    public void setUserMoney2024(BigDecimal userMoney2024) {
        this.userMoney2024 = userMoney2024;
    }

    public BigDecimal getUserMoney2025() {
        return userMoney2025;
    }

    public void setUserMoney2025(BigDecimal userMoney2025) {
        this.userMoney2025 = userMoney2025;
    }

    public int getUserAccumulate() {
        return userAccumulate;
    }

    public void setUserAccumulate(int userAccumulate) {
        this.userAccumulate = userAccumulate;
    }

    public String getUserPyName() {
        return userPyName;
    }

    public void setUserPyName(String userPyName) {
        this.userPyName = userPyName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
