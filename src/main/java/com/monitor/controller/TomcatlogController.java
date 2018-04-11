package com.monitor.controller;

import com.monitor.entity.SysUser;
import com.monitor.entity.Tomcatlog;
import com.monitor.service.TomcatlogService;
import com.monitor.utils.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/tomcatlog")
public class TomcatlogController {
	 private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TomcatlogService tomcatlogService;
	@RequestMapping("/list")
	public SimpleListObject<Tomcatlog> testtomcatlog(HttpSession session) {
		SimpleListObject<Tomcatlog> slo = new SimpleListObject<Tomcatlog>();
		JqQuery query = new JqQuery();
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("adminuser");
		query.setPage(1);
		query.setLimit(10);
//		JqCondition jqCondition = new JqCondition();
//		jqCondition.setDatatype(keytype);
//		jqCondition.setKey(keyname);
//		jqCondition.setValue(keyvalue);
		try {
			slo = tomcatlogService.query(query);
		}catch (Exception e){
			e.printStackTrace();
		}

		return slo;

	}
}