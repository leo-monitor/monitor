package com.monitor.service;

import java.util.List;

import com.monitor.dao.RolePermissionMapper;
import com.monitor.entity.RolePermission;
import com.monitor.utils.SimpleNetObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RolePermissionService {
	@Autowired
	private RolePermissionMapper RolePermissionmapper;
		

	private Logger logger=LoggerFactory.getLogger(RolePermissionService.class);

	public SimpleNetObject add(RolePermission entity){
		SimpleNetObject sno=new SimpleNetObject();
		try{
			sno.setResult(this.RolePermissionmapper.add(entity));
		}
		catch(Exception e){
			logger.error("RolePermissionService.add",e);
			sno.setResult(99);
			sno.setMessage("添加异常");		
		}
		return sno;
	}
	public SimpleNetObject update(RolePermission entity){
		SimpleNetObject sno=new SimpleNetObject();
		try {
			int result=this.RolePermissionmapper.update(entity);
			if(result==1){
				sno.setMessage("更新成功");
			}
			else{
				sno.setMessage("更新了"+result+"条记录");
			}
			sno.setResult(result);
		} catch (Exception e) {
			logger.error("RolePermissionService.update",e);
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
                                   this.RolePermissionmapper.delete(oid);
				}catch(Exception ex){}
				
				
				
			}
			sno.setResult(1);
			sno.setMessage("删除成功");
			
		} catch (Exception e) {
			
			logger.error("RolePermissionService.delete",e);
			sno.setResult(99);
			sno.setMessage("删除失败");
		}
		return sno;
	}


       public RolePermission getById(int oid ){
		try{
			return this.RolePermissionmapper.getById(oid);
		}
		catch(Exception e){
			logger.error("RolePermissionService.getById",e);
			return null;
		}
	}

}