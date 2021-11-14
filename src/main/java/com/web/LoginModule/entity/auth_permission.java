package com.web.LoginModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class auth_permission {

    private String permid;
    private String permname;
    private String permcode;
    private String shbj;
    private String scbj;
    private Date addtime;
    
}
