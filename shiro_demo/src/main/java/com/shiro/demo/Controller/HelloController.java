package com.shiro.demo.Controller;


import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello");
        return "hello";
    }

    @RequestMapping("/testThymtleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","Percent");
        return "test";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String login(){
        return "/login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){return "noAuth";}

    @RequestMapping("login")
    public String login(String name,String password,Model model){
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        org.apache.shiro.subject.Subject subject= SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        //3.执行登录方法

        try {
            subject.login(token);
            return "redirect:/testThymtleaf";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户不存在");
            System.out.println("用户名不存在");
            return "forward:/toLogin";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "forward:/toLogin";
        }
    }

}
