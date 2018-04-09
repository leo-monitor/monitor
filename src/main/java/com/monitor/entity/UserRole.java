package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/9.
 */
public @Data class UserRole implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private Integer user_id;
    private Integer role_id;
    private Date created_date;
    private Date updated_date;
}
