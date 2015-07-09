package com.springmvc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/Webroot/WEB-INF/conf/spring/applicationContext.xml");
		UserDao userDao=(UserDao) ctx.getBean("userDao");
		User user = new User();
        //添加两条数据
        user.setName("alalla");
        //user.setPassword("123456");
        userDao.insertUser(user);
        user.setName("alalla");
/*        ctx = new AnnotationConfigApplicationContext();
        UserService userDAO = (UserServiceImpl) ctx.getBean("userServiceImpl");
        userDAO.insertUser(user);*/
        System.out.println("添加成功");
	}

}
