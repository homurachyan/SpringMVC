package com.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	private UserDao userDao;
    
    public int insertUser(User user) throws Exception {
        // TODO Auto-generated method stub
    	if(user.getPassword()==null){
    		throw new Exception();
    	}else{
    		return userDao.insertUser(user);
    	}
    }
}
