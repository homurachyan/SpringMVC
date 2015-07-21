package com.springmvc.redis.dao;

import com.springmvc.entity.User;

public interface RedisUserDao {
	public User redisselectUser(User user);
	public int redisinsertUser(User user);
	public int redisupdateUser(User user);
	public int redisdeleteUser(int UserId);
}
