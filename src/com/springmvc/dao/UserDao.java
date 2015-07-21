package com.springmvc.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.springmvc.entity.User;

public interface UserDao {
	public User selectUser(User user);
	public int insertUser(User user);
	public int updateUser(User user);
	public int deleteUser(int UserId);
	public String selectUserId(String Username);
	public List<User> selectAllUser(Map param);
	public List<User> selectAll(PageBounds pageBounds);
}
