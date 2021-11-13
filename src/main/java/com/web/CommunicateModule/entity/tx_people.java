package com.web.CommunicateModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class tx_people {

    private String pid;
    private String pname;
    private String pstate;
    private String userid;
}
