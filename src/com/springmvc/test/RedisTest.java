package com.springmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.springmvc.entity.User;
import com.springmvc.redis.RedisClientTemplate;

import redis.clients.jedis.Jedis;

public class RedisTest {
	//@Autowired
	//private static RedisClientTemplate redisClientTemplate; //依赖注入
	
	private static ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");
	
	public static void main(String[] args) {
		
/*        RedisClientTemplate redisClientTemplate = (RedisClientTemplate) ctx.getBean("redisClientTemplate");
		redisClientTemplate.set("a", "123");
        System.out.println(redisClientTemplate.get("a"));*/

	}

}
