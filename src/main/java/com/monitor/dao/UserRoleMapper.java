package com.monitor.dao;


import com.monitor.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
	public int add(UserRole entity);
	public int delete(@Param("oid") int oid);
	public int update(UserRole entity);
	public UserRole getById(@Param("oid") int oid);

}