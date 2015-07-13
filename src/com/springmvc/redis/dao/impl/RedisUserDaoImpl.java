package com.springmvc.redis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.springmvc.entity.User;
import com.springmvc.redis.RedisClientTemplate;
import com.springmvc.redis.dao.RedisUserDao;
@Repository("redisUserDaoImpl")
public class RedisUserDaoImpl implements RedisUserDao{
	
	@Autowired
	private RedisClientTemplate redisClientTemplate; //依赖注入
	
	@Override
	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertUser(User user) {		
		redisClientTemplate.hset("user", "username", user.getName());
		redisClientTemplate.hset("user", "password", user.getPassword());
		return 1;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int UserId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
