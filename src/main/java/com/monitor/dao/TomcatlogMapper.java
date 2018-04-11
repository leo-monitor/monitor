package com.monitor.dao;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.monitor.entity.Tomcatlog;
import com.monitor.utils.JqQuery;
import org.apache.ibatis.annotations.Param;
public interface TomcatlogMapper {
	public int add(Tomcatlog entity);
	public PageList<Tomcatlog> list(Tomcatlog entity, PageBounds pageBounds);
	public int delete();
	public int update(Tomcatlog entity);
	public PageList<Tomcatlog> query(@Param("jqQuery") JqQuery query, PageBounds pageBounds);
	public Tomcatlog getById();
	//postgres数据库对应的导入执行语句
	int copylog(@Param("file") String file);
	//mysql数据库对应的导入执行语句
	int copylogformysql(@Param("file") String file);
}