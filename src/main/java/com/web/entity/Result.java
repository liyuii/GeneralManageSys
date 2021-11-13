package com.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<T> {

    private String code;           //用于返回增删结果：0否 1是
    private String message;     //操作对应的提示
    private T data;

}
