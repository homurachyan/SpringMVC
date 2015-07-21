package com.springmvc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
    
    public int insertUser(User user) throws Exception {
    	if(user.getPassword()==null||user.getName()==null){
    		throw new Exception();
    	}else{
    		userDao.insertUser(user);
    	}
    	return 1;
    }

	@Override
	public User selectUser(User user) {
		return userDao.selectUser(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> selectAllUser(Map param) {
		// TODO Auto-generated method stub
		return userDao.selectAllUser(param);
	}

	@Override
	public List<User> selectAll(PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return userDao.selectAll(pageBounds);
	}
}
