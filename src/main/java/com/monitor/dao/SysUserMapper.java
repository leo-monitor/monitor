package com.monitor.dao;


import com.monitor.entity.SysUser;
import org.apache.ibatis.annotations.Param;
public interface SysUserMapper {
	public int add(SysUser entity);
	public int delete(@Param("oid") int oid);
	public int update(SysUser entity);
	public SysUser getById(@Param("oid") int oid);
	public SysUser getByUsername(@Param("username") String username);
}