package com.monitor.dao;

import com.monitor.entity.RolePermission;
import org.apache.ibatis.annotations.Param;
public interface RolePermissionMapper {
	public int add(RolePermission entity);
	public int delete(@Param("oid") int oid);
	public int update(RolePermission entity);
	public RolePermission getById(@Param("oid") int oid);
}