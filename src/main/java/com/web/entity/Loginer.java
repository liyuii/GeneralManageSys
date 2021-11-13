package com.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loginer {

    private String loginId;
    private String loginName;
    private String loginPassword;
    /**
     * 用户对应的角色集合
     */
//    private Set<Role> roles;
}
