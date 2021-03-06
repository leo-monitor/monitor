package com.monitor.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.monitor.service.TomcatlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.entity.Tomcatlog;
import com.monitor.utils.IoUtil;
import com.monitor.utils.SimpleNetObject;

@RestController
@RequestMapping("/admin")
public class AdminController {
	 private final Logger logger = LoggerFactory.getLogger(getClass());
	@Value("${spring.profiles.databasetype}")
	private String databasetype;
	@Value("${spring.profiles.logfilepath}")
	private String logfilepath;
	@Value("${spring.profiles.targetfilepath}")
	private String targetfilepath;
	@Autowired
	private TomcatlogService tomcatlogService;

	//转存日志指定最后几行，如果不指定行数默认1000行
	@RequestMapping("/copytomcatloglastline")
	public SimpleNetObject copytomcatloglastline(String readfilepath, String writefilepath, long lines) {
		SimpleNetObject sno = new SimpleNetObject();

		if (lines == 0) {
			lines = 1000;
		}
		if (null == readfilepath) {
			SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = sfDateFormat.format(new Date());
			readfilepath = logfilepath+"/localhost_access_log." + date + ".txt";
		}

		if (null == writefilepath) {
			SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = sfDateFormat.format(new Date());
			writefilepath = targetfilepath+"/accesslog." + date + ".txt";
		}
		int totallines = 0;
		try {
			totallines = IoUtil.countLines(readfilepath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (totallines < lines) {
			lines = totallines;
		}
		try {
			long time = IoUtil.copytomcatloglastline(readfilepath, writefilepath, lines);
			Thread.sleep(2000);
			tomcatlogService.copylog(writefilepath,databasetype);
			sno.setMessage("转存日志耗时" + time);
			logger.info("info test");
			logger.debug("debug test");
			logger.warn("warn test");
			logger.error("error test");
			return sno;
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleNetObject(99, "转存异常");
		}
	}
//	 public static void main(String[] args) {
//	 SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//	 String date =sfDateFormat.format(new Date());
//	 String writefilepath =
//	 "/usr/software/ymall/test/apache-tomcat-7.0.53/logs/accesslog."+date+".txt";
//	 System.out.println(writefilepath);
//	 }
	@RequestMapping("/testtomcatlog")
	public SimpleNetObject testtomcatlog() {
		SimpleNetObject sno = new SimpleNetObject();
//		List<Tomcatlog> list = tomcatlogService.testget();
//		sno.setData(list);
//		logger.info("info test");
//		logger.debug("debug test");
//		logger.warn("warn test");
//		logger.error("error test");
		sno=new SimpleNetObject(1,"success");
		return sno;
	}
}