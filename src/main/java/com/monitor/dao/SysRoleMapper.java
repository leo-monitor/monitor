package com.monitor.dao;

import com.monitor.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
	public int add(SysRole entity);
	public int delete(@Param("oid") int oid);
	public int update(SysRole entity);
	public SysRole getById(@Param("oid") int oid);
	public List<String> getRoleNameByUserId(@Param("user_id") int user_id);
}