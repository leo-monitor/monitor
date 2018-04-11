package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */
public @Data class SysUser implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int oid;
    private String username;
    private String password;
    private String salt;//加密密码的盐
    private int user_type;
    private Date created_date;
    private Date updated_date;
    private Date lastlogin_date;
    private List<String> rolenamelist;
    private List<String> permissionnamelist;
}
