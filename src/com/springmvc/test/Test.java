package com.springmvc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import com.springmvc.service.impl.UserServiceImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/Webroot/WEB-INF/conf/spring/applicationContext.xml");
		//UserDao userDao=(UserDao) ctx.getBean("userDao");
		//ApplicationContext ctx = new AnnotationConfigApplicationContext();
		UserService userService = (UserService) ctx.getBean("userService");
		
		User user = new User();
        user.setName("xiaoming");
        user.setPassword("123");
        userService.selectUser(user);
        
        System.out.println(user.getPassword());
	}

}
