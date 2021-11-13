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
public class tx_msg {

    private String msgid;//消息id
    private Object msgContent;//消息格式
    private String groupId;//暂时不用
    private String fromId;//
    private String fromName;
    private String toId;
    private String toName;
    private String msgType;//system | group | single
    private Date addTime;

}
