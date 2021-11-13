package com.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    private String roleId;
    private String roleName;
    private String active;
    private String creator;
    private Date ctrateTime;
    /**
     * 角色对应权限集合
     */
    private Set<Function> functions;
}
