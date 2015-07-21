package com.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.springmvc.entity.Session;
import com.springmvc.entity.User;
import com.springmvc.redis.dao.RedisUserDao;
import com.springmvc.redis.dao.impl.RedisUserDaoImpl;
import com.springmvc.service.UserService;
import com.springmvc.util.SessionUtil;
import com.springmvc.util.SpringContextHolder;

@Controller
public class LoginController {
	//@Resource(name="UserService")
	private UserService userService;  //由于事务控制，controller和service层分离，导致两者上下文环境不同，不能注解注入，需要手动注入
	@Autowired
	private RedisUserDao redisUserDaoImpl;

    @RequestMapping(value="/login",method=RequestMethod.POST)  
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	userService = (UserService) SpringContextHolder.getBean("userService");
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
        		SessionUtil sessionutil = (SessionUtil)SpringContextHolder.getBean("sessionUtil");
        		Session session = sessionutil.initSession(username);
        		sessionutil.setSession(session);
        		ModelAndView mav = new ModelAndView("succ");  
        		mav.addObject("token", session.getSessionid());
        		mav.addObject("userid", user.getId());
        		mav.addObject("username", user.getName());
                return mav;  
        	}else{
        		   return new ModelAndView("login");
        	}
        }  
        return new ModelAndView("index");  
    }  

    //直接通过空视图传输json
    @RequestMapping(value="/json")
    public void showInfoJson(HttpServletRequest request,
            HttpServletResponse response) {
        String result ="{'name':'xiaoming','password':'123'}";//user
            //接到前台传到的数据，并拼接成新的json对象 
        response.setContentType("application/json;charset=UTF-8");//设置response的传输格式为json 
        try { 
             PrintWriter out = response.getWriter(); 
             //out.write(result);//给页面上传输json对象 
             out.println("{\"uuid\":\"123\",\"name\":\"456\"}");
        } catch (IOException e) { 
             e.printStackTrace(); 
        } 
        //return null;
    }  
    @RequestMapping(value = "/hello")
	// 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
	public ModelAndView jsonview(HttpServletRequest request, HttpServletResponse response) {
    	List<User> list = new ArrayList<User>();
    	userService = (UserService) SpringContextHolder.getBean("userService");
    	Map map = new HashMap();
    	map.put("start","0");
    	map.put("pagesize", "3");
		list = userService.selectAllUser(map);
/*    	int page = 1; //页号
    	int pageSize = 5; //每页数据条数
    	PageBounds pageBounds = new PageBounds(page, pageSize);*/
    	//list = userService.selectAll(pageBounds);
        //同时写入redis
        redisUserDaoImpl.redisinsertUser(new User());
        ModelAndView mav = new ModelAndView("hello");  
        mav.addObject("results", list);
        return mav;  
	}
    //使用数据绑定@RequestBody/@ResponseBody
    //@RequestBody 用于将HttpServletRequest的getInputStream()内容绑定到入参
    //@ResponseBody 用于将方法的返回值作为响应体
    //注意：他们都只能访问报文体，不能访问报文头
    @RequestMapping(value = "/xhello", method = RequestMethod.GET, produces="application/json")
	// 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
	public @ResponseBody List<User> hello(HttpServletRequest request, HttpServletResponse response) {
    	List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setName("xiaoming");
		user1.setPassword("123");
		list.add(user1);
		User user2 = new User();
		user2.setName("xiaowang");
		user2.setPassword("456");
		list.add(user2);
        //同时写入redis
        redisUserDaoImpl.redisinsertUser(user1);
		//System.out.println(request.getSession().toString());
        return list;  
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
