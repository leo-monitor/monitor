package com.monitor.service;

import java.util.List;

import com.monitor.dao.SysPermissionMapper;
import com.monitor.entity.SysPermission;
import com.monitor.utils.SimpleNetObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysPermissionService {
	@Autowired
	private SysPermissionMapper permissionmapper;
		

	private Logger logger=LoggerFactory.getLogger(SysPermissionService.class);

	public SimpleNetObject add(SysPermission entity){
		SimpleNetObject sno=new SimpleNetObject();
		try{
			sno.setResult(this.permissionmapper.add(entity));
		}
		catch(Exception e){
			logger.error("PermissionService.add",e);
			sno.setResult(99);
			sno.setMessage("添加异常");		
		}
		return sno;
	}
	public SimpleNetObject update(SysPermission entity){
		SimpleNetObject sno=new SimpleNetObject();
		try {
			int result=this.permissionmapper.update(entity);
			if(result==1){
				sno.setMessage("更新成功");
			}
			else{
				sno.setMessage("更新了"+result+"条记录");
			}
			sno.setResult(result);
		} catch (Exception e) {
			logger.error("PermissionService.update",e);
			sno.setResult(99);
			sno.setMessage("异常");
		}
		return sno;
	}
	
	@Transactional
	public SimpleNetObject delete(String oids){
		SimpleNetObject sno=new SimpleNetObject();
		try {
			String[] arrOids=oids.split(",");
			for(int i=0;i<arrOids.length;i++){
				
				String id=arrOids[i];
				
				try
				{
					int oid=Integer.parseInt(id);
                                   this.permissionmapper.delete(oid);
				}catch(Exception ex){}
				
				
				
			}
			sno.setResult(1);
			sno.setMessage("删除成功");
			
		} catch (Exception e) {
			
			logger.error("PermissionService.delete",e);
			sno.setResult(99);
			sno.setMessage("删除失败");
		}
		return sno;
	}


       public SysPermission getById(int oid ){
		try{
			return this.permissionmapper.getById(oid);
		}
		catch(Exception e){
			logger.error("PermissionService.getById",e);
			return null;
		}
	}
	public List<String> getPermissionNameByUserId(int user_id ){
		try{
			return this.permissionmapper.getPermissionNameByUserId(user_id);
		}
		catch(Exception e){
			logger.error("PermissionService.getById",e);
			return null;
		}
	}


}