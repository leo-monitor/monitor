package com.monitor.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/23.
 */
@Data
public class User {
    private String username;
    private String password;
    private int userType;
    private Date createdDate;
    private Date updatedDate;
}
