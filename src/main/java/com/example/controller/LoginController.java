package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录业务控制类
 * @Author fhb
 * @Date 2020年2月10日12:02:33
 * @Version 1.0
 **/
//在templates目录下所有的页面只能通过controller来跳转
//这个需要模板引擎的支持(可以在pom.xml中配置下载)
@Controller
public class LoginController {
    //登录页请求
    @RequestMapping("/login.html")
    public String LoginHtml(){
        System.out.println("登录页请求");
        return "/login/login";
    }

    //首页请求
    @RequestMapping({"/","/index.html"})
    public String indexHtml(){
        System.out.println("首页请求");
        return "/index/index";
    }

    //退出登录请求
    @RequestMapping("/logout.do")
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request) throws Exception{
        System.out.println("logout.do");
        Map map = new HashMap();
        HttpSession session = request.getSession();
        System.out.println("退出登录请求");
        session.removeAttribute("loginUser");//这里的loginUser是当初登录成功时在session设置的用户名
        map.put("flag",true);
        return map;
    }

    /**
     * 登录请求
     * @param userName
     * @param password
     * @throws Exception
     */
    @RequestMapping("/login.do")
    @ResponseBody   //加了这个注解就不走视图解析器了，这个要特别注意
    public Map<String,Object> login(String userName, String password, HttpSession session) throws Exception{
        Map map = new HashMap();
        if(!StringUtils.isEmpty(userName)&&"123456".equals(password)){
            System.out.println("登录请求成功!");
            session.setAttribute("loginUser",userName);
            map.put("flag",true);
            return map;
        }else{
            System.out.println("登录请求失败!");
            map.put("flag",false);
            return map;
        }
    }
}
