package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/9.
 */

public @Data class RolePermission implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private long role_id;
    private long permission_id;
}
