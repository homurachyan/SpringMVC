package com.springmvc.redis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

	 private static final Logger log = Logger.getLogger(RedisDataSourceImpl.class);
	 private static ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:WEB-INF/conf/spring/applicationContext.xml");

	    //@Resource(name="shardedJedisPool") 
	    //@Qualifier("shardedJedisPool")
	    private static ShardedJedisPool shardedJedisPool = (ShardedJedisPool)ctx.getBean("shardedJedisPool");

	    public ShardedJedis getRedisClient() {
	        try {
	            ShardedJedis shardJedis = shardedJedisPool.getResource();
	            return shardJedis;
	        } catch (Exception e) {
	            log.error("getRedisClent error", e);
	        }
	        return null;
	    }

	    public void returnResource(ShardedJedis shardedJedis) {
	        shardedJedisPool.returnResource(shardedJedis);
	    }

	    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
	        if (broken) {
	            shardedJedisPool.returnBrokenResource(shardedJedis);
	        } else {
	            shardedJedisPool.returnResource(shardedJedis);
	        }
	    }

}
