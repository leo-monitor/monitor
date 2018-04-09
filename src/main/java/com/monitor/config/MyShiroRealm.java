//package com.monitor.config;
//
//
//import com.monitor.entity.SysPermission;
//import com.monitor.entity.SysRole;
//import com.monitor.entity.SysUser;
//import com.monitor.service.*;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyShiroRealm extends AuthorizingRealm {
//
//    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
//    @Autowired
//    private SysUserService sysUserService;
//    @Autowired
//    private SysRoleService sysRoleService;
//    @Autowired
//    private SysPermissionService sysPermissionService;
//    @Autowired
//    private UserRoleService userRoleService;
//    @Autowired
//    private RolePermissionService rolePermissionService;
//    //权限控制
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        SysUser userInfo  = (SysUser) principals.getPrimaryPrincipal();
//        for(SysRole role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRolename());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermissionname());
//            }
//        }
//        return authorizationInfo;
//    }
//
//    /*登录认证,主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
//            throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//       // logger.info("验证当前Subject时获取到token为：" + token.toString());
//        //查出是否有此用户
//        SysUser hasUser = sysUserService.selectByUsername(token.getUsername());
////        String md5Pwd = new Md5Hash("123", "lucare",2).toString();
//        if (hasUser != null) {
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            List<SysRole> rlist = userRoleService.(hasUser.getOid());//获取用户角色
//            List<SysPermission> plist = uPermissionDao.findPermissionByUid(hasUser.getOid());//获取用户权限
//            List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
//            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
//            for (URole role : rlist) {
//                roleStrlist.add(role.getName());
//            }
//            for (UPermission uPermission : plist) {
//                perminsStrlist.add(uPermission.getName());
//            }
//            hasUser.setRoleStrlist(roleStrlist);
//            hasUser.setPerminsStrlist(perminsStrlist);
////            Session session = SecurityUtils.getSubject().getSession();
////            session.setAttribute("user", hasUser);//成功则放入session
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            return new SimpleAuthenticationInfo(hasUser, hasUser.getPswd(), getName());
//        }
//        return null;
//    }
////        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
////
////        //获取用户的输入的账号.
////        String username = (String)token.getPrincipal();
////        System.out.println(token.getCredentials());
////        //通过username从数据库中查找 User对象，如果找到，没找到.
////        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
////        SysUser userInfo = userInfoService.findByUsername(username);
////        System.out.println("----->>userInfo="+userInfo);
////        if(userInfo == null){
////            return null;
////        }
////        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
////                userInfo, //用户名
////                userInfo.getPassword(), //密码
////                getName()  //realm name
////        );
////        return authenticationInfo;
////    }
//
//}