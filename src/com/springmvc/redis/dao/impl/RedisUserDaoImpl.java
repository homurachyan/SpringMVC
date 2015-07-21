package com.springmvc.redis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.springmvc.entity.User;
import com.springmvc.redis.RedisClientTemplate;
import com.springmvc.redis.RedisService;
import com.springmvc.redis.dao.RedisUserDao;
@Repository("redisUserDaoImpl")
public class RedisUserDaoImpl implements RedisUserDao{
	private static ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");	
	//@Autowired
	private RedisService redisService; //依赖注入
	
	@Override
	public User redisselectUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int redisinsertUser(User user) {		
		redisService = (RedisService) ctx.getBean("redisService");
		redisService.set("username", "xiaoming");
		return 1;
	}

	@Override
	public int redisupdateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int redisdeleteUser(int UserId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
