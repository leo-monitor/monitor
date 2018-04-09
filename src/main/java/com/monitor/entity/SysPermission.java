package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public @Data class SysPermission implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private long oid;//主键.
    private String permissionname;//名称.
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private List<SysRole> roles;
    private Date created_date;
    private Date updated_date;

}