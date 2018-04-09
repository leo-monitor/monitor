package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 123456789L;
	private Integer oid;
	private String username;
	private String password;
	private String salt;
	private String user_type;
	private Date created_date;
	private Date updated_date;
}