package com.springmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.springmvc.entity.User;

import redis.clients.jedis.Jedis;

public class RedisTest {
	@Autowired
	private static RedisTemplate redisTemplate; //依赖注入
	
	//private ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");
	
	public static void main(String[] args) {
		
		final User user = new User();
		redisTemplate.execute(new RedisCallback<Object>() {  
	        @Override  
	        public Object doInRedis(RedisConnection connection)  
	                throws DataAccessException {  
	            connection.set(  
	                    redisTemplate.getStringSerializer().serialize(  
	                            "user.uid." + user.getName()),  
	                    redisTemplate.getStringSerializer().serialize(  
	                            user.getPassword()));  
	            return null;  
	        }  
	    });  

	}

}
