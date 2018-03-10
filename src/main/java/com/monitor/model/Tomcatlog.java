package com.monitor.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tomcatlog{


	private String callIp;

	private String callUrl;

	private int callTime;

	private Date callDate;

	private String callStatus;

	private Date createdDate;
	
}