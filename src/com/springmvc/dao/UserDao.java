package com.springmvc.dao;

import com.springmvc.entity.User;

public interface UserDao {
	public User selectUser(User user);
	public int insertUser(User user);
	public int updateUser(User user);
	public int deleteUser(int UserId);
}
