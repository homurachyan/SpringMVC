package com.springmvc.test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.RedisTemplate;

import com.springmvc.redis.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class testSentinel {
	private static ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");
	
	public static void main(String[] args) {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.101.128:26379");  

        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
                                       //new JedisSentinelPool(MASTER_NAME, sentinels, new GenericObjectPoolConfig(), 1000, "foobared", 2);

        Jedis jedis = sentinelPool.getResource();

        System.out.println("current Host:" + sentinelPool.getCurrentHostMaster());

        RedisTemplate template = ctx.getBean(RedisTemplate.class);
        RedisTemplate redisTemplate = ctx.getBean("RedisTemplate", RedisTemplate.class);
        for (RedisServer m : template.getConnectionFactory().getSentinelConnection().masters()) {
                System.out.println(m);
        }  
        //RedisService redisService = (RedisService) ctx.getBean("redisService");
        //stringRedisTemplate.delete("myStr");
        //redisService.set("myStr", "http://yjmyzz.cnblogs.com/");
        //System.out.println(redisService.get("myStr"));
        System.out.println(redisTemplate.opsForHash().get("user:1", "sessionid"));
        System.out.println("---------------");
   }

}
