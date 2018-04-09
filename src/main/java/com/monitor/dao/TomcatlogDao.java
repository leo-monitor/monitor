package com.monitor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.monitor.entity.Tomcatlog;


public interface TomcatlogDao {
	//postgres数据库对应的导入执行语句
	int copylog(@Param("file") String file);
	//mysql数据库对应的导入执行语句
	int copylogformysql(@Param("file") String file);
	//测试获取日志
	List<Tomcatlog> testget();
}
