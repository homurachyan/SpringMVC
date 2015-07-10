package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class UserController {
 
    //@RequestMapping(value="login",method=RequestMethod.POST)  
	@RequestMapping("/getUser")
    public ModelAndView getAlluser(){  
        //指定要返回的页面为alluser.jsp  
        ModelAndView mav = new ModelAndView("alluser");  
        //取model
        
        //将参数返回给页面  
/*        mav.addObject("name",username);  
        mav.addObject("password", password); */ 
        return mav;   
    }  
}

