package com.springmvc.service;

import com.springmvc.entity.User;

public interface UserService {
	public int insertUser(User user) throws Exception;
	public User selectUser(User user);
}
