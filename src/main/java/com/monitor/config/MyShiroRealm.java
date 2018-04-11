package com.monitor.config;


import com.monitor.entity.SysPermission;
import com.monitor.entity.SysRole;
import com.monitor.entity.SysUser;
import com.monitor.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;


    /*登录认证,主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
       // logger.info("验证当前Subject时获取到token为：" + token.toString());
        //查出是否有此用户
        SysUser hasUser = sysUserService.getByUsername(token.getUsername());
//        String md5Pwd = new Md5Hash("123", "lucare",2).toString();
        if (hasUser != null) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            List<String> roleStrlist = sysRoleService.getRoleNameByUserId(hasUser.getOid());//获取用户角色
            List<String> perminsStrlist=sysPermissionService.getPermissionNameByUserId(hasUser.getOid());//用户的权限集合
            hasUser.setRolenamelist(roleStrlist);
            hasUser.setPermissionnamelist(perminsStrlist);
//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("user", hasUser);//成功则放入session
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(), getName());
        }
        return null;
    }

    //权限控制
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        SysUser userInfo  = (SysUser) principals.getPrimaryPrincipal();
//        if (null!=userInfo){
//            authorizationInfo.addRoles(userInfo.getRolenamelist());
//            authorizationInfo.addStringPermissions(userInfo.getPermissionnamelist());
//            return authorizationInfo;
//        }
        return null;
    }

}