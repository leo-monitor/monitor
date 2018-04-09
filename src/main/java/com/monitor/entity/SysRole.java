package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public @Data class SysRole implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private long oid; // 编号
    private String rolename; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    //角色 -- 权限关系：多对多关系;
    private List<SysPermission> permissions;
    // 用户 - 角色关系定义;
    private List<SysUser> users;// 一个角色对应多个用户
    private Date created_date;
    private Date updated_date;

}