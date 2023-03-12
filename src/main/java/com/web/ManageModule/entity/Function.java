package com.web.ManageModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Function {

    private String funId;
    private String funName;
    private String funCode;
    private String funUrl;
    private String funOrder;
    private String active;
}
