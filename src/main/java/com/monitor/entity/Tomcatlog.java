package com.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public @Data class Tomcatlog implements Serializable {
	private static final long serialVersionUID = 123456789L;

	private String call_ip;

	private String call_url;

	private Integer call_time;

	private Date call_date;

	private String call_status;
	
}