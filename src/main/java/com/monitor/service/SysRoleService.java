package com.monitor.service;

import java.util.List;

import com.monitor.dao.SysRoleMapper;
import com.monitor.entity.SysRole;
import com.monitor.utils.SimpleNetObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SysRoleService {
	@Autowired
	private SysRoleMapper rolemapper;
		

	private Logger logger=LoggerFactory.getLogger(SysRoleService.class);

	public SimpleNetObject add(SysRole entity){
		SimpleNetObject sno=new SimpleNetObject();
		try{
			sno.setResult(this.rolemapper.add(entity));
		}
		catch(Exception e){
			logger.error("RoleService.add",e);
			sno.setResult(99);
			sno.setMessage("添加异常");		
		}
		return sno;
	}
	public SimpleNetObject update(SysRole entity){
		SimpleNetObject sno=new SimpleNetObject();
		try {
			int result=this.rolemapper.update(entity);
			if(result==1){
				sno.setMessage("更新成功");
			}
			else{
				sno.setMessage("更新了"+result+"条记录");
			}
			sno.setResult(result);
		} catch (Exception e) {
			logger.error("RoleService.update",e);
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
                                   this.rolemapper.delete(oid);
				}catch(Exception ex){}
				
				
				
			}
			sno.setResult(1);
			sno.setMessage("删除成功");
			
		} catch (Exception e) {
			
			logger.error("RoleService.delete",e);
			sno.setResult(99);
			sno.setMessage("删除失败");
		}
		return sno;
	}

       public SysRole getById(int oid ){
		try{
			return this.rolemapper.getById(oid);
		}
		catch(Exception e){
			logger.error("RoleService.getById",e);
			return null;
		}
	}
	public List<String> getRoleNameByUserId(int user_id ){
		try{
			return this.rolemapper.getRoleNameByUserId(user_id);
		}
		catch(Exception e){
			logger.error("RoleService.getById",e);
			return null;
		}
	}

}