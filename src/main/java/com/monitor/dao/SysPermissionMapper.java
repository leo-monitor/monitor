package com.monitor.dao;


import com.monitor.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {
	public int add(SysPermission entity);
	public int delete(@Param("oid") int oid);
	public int update(SysPermission entity);
	public SysPermission getById(@Param("oid") int oid);
	public List<String> getPermissionNameByUserId(@Param("user_id") int user_id);
}