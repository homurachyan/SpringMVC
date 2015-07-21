package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.entity.GlobalConfig;

@Controller
public class GlobalConfigController {
	

	@RequestMapping(value="/globalconfig",method=RequestMethod.GET)  
    public ModelAndView getGlobalConfig(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<GlobalConfig> list = null;
		ModelAndView mav = new ModelAndView("globalconfig");  
		mav.addObject("results", list);
		return mav;  		
	}
	
	@RequestMapping(value="/globalconfig.json",method=RequestMethod.POST)  
    public ModelAndView setGlobalConfig(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //修改全局参数
		request.getParameter("");
		
		//mysql，redis各存一份
		//redis格式gid:propertyval
		return null;  		
	}
}
