package com.springmvc.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.springmvc.entity.User;

public interface UserService {
	public int insertUser(User user) throws Exception;
	public User selectUser(User user);
	public List<User> selectAllUser(Map param);
	public List<User> selectAll(PageBounds pageBounds);
}
