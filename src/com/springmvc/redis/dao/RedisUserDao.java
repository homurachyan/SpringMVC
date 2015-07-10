package com.springmvc.redis.dao;

import com.springmvc.entity.User;

public interface RedisUserDao {
	public User selectUser(User user);
}
