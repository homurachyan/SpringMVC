package com.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
    
    public int insertUser(User user) throws Exception {
		User temp = new User();
		temp.setName(user.getName());
		temp.setPassword(user.getPassword());
		userDao.insertUser(temp);
		System.out.println("insert succ");
		temp.setPassword(null);
		
    	if(temp.getPassword()==null){
    		throw new Exception();
    	}else{
    		return 1;
    	}
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
}
