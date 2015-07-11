package com.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
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
      
    
    @RequestMapping(value="/login2")
    public Object getjson(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
/*        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        System.out.println("使用Spring内置的支持："+username +"--->" + password);

        Map<String, String> map = new HashMap<String, String>();
        if(username.equals("xiaoming") && password.equals("123"))
        {
            map.put("results", "login success");
        }
        else
        {
            map.put("results", "login fail");
        }        
        */
/*        ModelAndView modelView=new ModelAndView();  
        Map<String,Object> modelMap=new HashMap<String,Object>();  
        modelMap.put("username", "xiaoming");  
        modelMap.put("password", "123");  
        modelView.addAllObjects(modelMap);  
        return modelView; */
    	//response.setCharacterEncoding("UTF-8");
        //response.setContentType("application/json");
		return "json";
	}  
    
    @RequestMapping(value="/json")
    public void showInfoJson(HttpServletRequest request,
            HttpServletResponse response) {
        String result ="{'name':'xiaoming','password':'123'}";//user
            //接到前台传到的数据，并拼接成新的json对象 
        response.setContentType("application/json");//设置response的传输格式为json 
        System.out.println(result); 
        try { 
             PrintWriter out = response.getWriter(); 
             out.write(result);//给页面上传输json对象 
        } catch (IOException e) { 
             e.printStackTrace(); 
        } 
    }      
    @RequestMapping(value = "/hello.json", method = RequestMethod.GET,produces = "application/json")
	// 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
	public @ResponseBody User hello() {
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");
    	userService = (UserService) ctx.getBean("userService");
    	User user = null;
		User temp = new User();
		temp.setName("xiaoming");
		temp.setPassword("123");
		user = userService.selectUser(temp);
        return user;  
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
