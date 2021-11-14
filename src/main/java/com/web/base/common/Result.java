package com.web.base.common;

public class Result<T> {

    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result<T> setData(String code,String msg,T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
        return this;
    }
}
