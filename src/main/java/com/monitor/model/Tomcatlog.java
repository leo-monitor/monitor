package com.monitor.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tomcatlog{


	private String callIp;

	private String callUrl;

	private Integer callTime;

	private Long callDate;

	private String callStatus;
	
}