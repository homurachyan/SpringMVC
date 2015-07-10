package com.springmvc.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;

@Controller
public class LoginController {
	//@Autowired
	//@Qualifier("userService")
	private UserService userService;  //业务层注入
	
    @RequestMapping(value="/login",method=RequestMethod.POST)  
    public ModelAndView login(String username,String password) throws Exception{  
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");
    	userService = (UserService) ctx.getBean("userService");
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
        if(this.checkParams(new String[]{username,password})){  
        	//通过业务层进行查询判断
        	User user = null;
    		User temp = new User();
    		temp.setName(username);
    		temp.setPassword(password);
    		//userService.insertUser(temp); //Service层事务控制展示

    		user = userService.selectUser(temp);
        	
        	if(user!=null){
        		ModelAndView mav = new ModelAndView("succ");  
                mav.addObject("username", user.getName());
                return mav;  
        	}else{
        		   return new ModelAndView("hello");
        	}
        }  
        return new ModelAndView("index");  
    }  
      
    /*** 
     * 验证参数是否为空 
     * @param params 
     * @return 
     */  
    private boolean checkParams(String[] params){  
        for(String param:params){  
            if(param==""||param==null||param.isEmpty()){  
                return false;  
            }  
        }  
        return true;  
    }
}
