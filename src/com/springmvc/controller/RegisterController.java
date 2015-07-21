package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	

    @RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
    	 ModelAndView mav = new ModelAndView("register");  
    	 //获取注册提交信息
    	 //验证
    	 //
         mav.addObject("results", "123");
         return mav;  
    }
    
    @RequestMapping(value = "/isregistered")
	public ModelAndView isRegistered(HttpServletRequest request, HttpServletResponse response) {
    	 ModelAndView mav = new ModelAndView("isregistered");  
    	 //获取注册提交信息
    	 //验证,一般是用户名/邮箱/手机号
    	 //
         mav.addObject("results", "123");
         return mav;  
    }
}
