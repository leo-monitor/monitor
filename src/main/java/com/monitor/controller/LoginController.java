package com.monitor.controller;

import com.monitor.entity.SysUser;
import com.monitor.service.SysUserService;
import com.monitor.utils.SimpleNetObject;
import com.monitor.utils.StringTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.monitor.utils.StringTool.checkIfNull;

/**
 * Created by Administrator on 2018/2/23.
 */
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysUserService userService;
    @RequestMapping("/login")
    public String loginpage() {

        return "login";
    }
    @RequestMapping("/signin")
    @ResponseBody
    public SimpleNetObject signin(String username,
                         String password) throws Exception {
        if(StringTool.checkNullOrEmpty(username,password)){
            return StringTool.setNullMessages(
                    checkIfNull(username,password),
                    "用户名不能为空","密码不能为空");
        }
        SimpleNetObject sno = new SimpleNetObject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }  catch (IncorrectCredentialsException ice) {
        // 捕获密码错误异常
            ice.printStackTrace();
            return new SimpleNetObject(99,"密码异常");
    } catch (UnknownAccountException uae) {
        // 捕获未知用户名异常
            uae.printStackTrace();
            return new SimpleNetObject(98,"用户名异常");
    } catch (ExcessiveAttemptsException eae) {
        // 捕获错误登录过多的异常
            eae.printStackTrace();
            return new SimpleNetObject(97,"登录过多");
    }
        SysUser user = userService.getByUsername(username);
            subject.getSession().setAttribute("adminuser",user);
        return new SimpleNetObject(1,"登录成功");
    }

    @RequestMapping("/logout")
    @ResponseBody
    public SimpleNetObject logout()  {
        SimpleNetObject sno = new SimpleNetObject();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        }catch (Exception e){
            e.printStackTrace();
        }
        Subject subject = SecurityUtils.getSubject();
        if (null!=subject.getSession().getAttribute("adminuser")){
            return new SimpleNetObject(99,"注销失败");
        }else {
            return new SimpleNetObject(1,"注销成功");
        }
    }

    @RequestMapping("/testshirosession")
    @ResponseBody
    public SimpleNetObject testshirosession() throws Exception {
        SimpleNetObject sno = new SimpleNetObject();
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getSession().getAttribute("adminuser");
        if (null!=user){
            System.out.println(user.toString());
        }
        sno.setData(user);
        return sno;
    }
}
