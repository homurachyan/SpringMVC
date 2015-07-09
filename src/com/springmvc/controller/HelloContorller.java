package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class HelloContorller {       
   //hello world例子  
   @RequestMapping(value="/hello")  
   public String hello(){  
       System.out.println("spring mvc hello world!");  
       return "hello";  
   }  
}  
