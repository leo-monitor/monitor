package com.monitor.service;

import java.util.List;

import com.monitor.dao.SysUserMapper;
import com.monitor.entity.SysUser;
import com.monitor.utils.SimpleNetObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SysUserService {
	@Autowired
	private SysUserMapper usermapper;
		

	private Logger logger=LoggerFactory.getLogger(SysUserService.class);

	public SimpleNetObject add(SysUser entity){
		SimpleNetObject sno=new SimpleNetObject();
		try{
			sno.setResult(this.usermapper.add(entity));
		}
		catch(Exception e){
			logger.error("UserService.add",e);
			sno.setResult(99);
			sno.setMessage("添加异常");		
		}
		return sno;
	}
	public SimpleNetObject update(SysUser entity){
		SimpleNetObject sno=new SimpleNetObject();
		try {
			int result=this.usermapper.update(entity);
			if(result==1){
				sno.setMessage("更新成功");
			}
			else{
				sno.setMessage("更新了"+result+"条记录");
			}
			sno.setResult(result);
		} catch (Exception e) {
			logger.error("UserService.update",e);
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
                                   this.usermapper.delete(oid);
				}catch(Exception ex){}
				
				
				
			}
			sno.setResult(1);
			sno.setMessage("删除成功");
			
		} catch (Exception e) {
			
			logger.error("UserService.delete",e);
			sno.setResult(99);
			sno.setMessage("删除失败");
		}
		return sno;
	}
//	public List<User> list(SysUser entity,PageBounds pageBounds){
//
//		try{
//			return this.usermapper.list(entity,pageBounds);
//		}
//		catch(Exception e){
//			logger.error("UserService.list",e);
//			return null;
//		}
//	}
//
public SysUser getById(int oid ){
	try{
		return this.usermapper.getById(oid);
	}
	catch(Exception e){
		logger.error("UserService.getById",e);
		return null;
	}
}
	public SysUser getByUsername(String username ){
		try{
			return this.usermapper.getByUsername(username);
		}
		catch(Exception e){
			logger.error("UserService.getById",e);
			return null;
		}
	}
}