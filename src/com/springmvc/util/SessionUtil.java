package com.springmvc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.Session;
import com.springmvc.redis.RedisClientTemplate;
import com.springmvc.redis.RedisService;
import com.springmvc.redis.RedisServiceImpl;
@Service("sessionUtil")
public class SessionUtil {
	
	@Autowired
	private RedisService redisService;//依赖注入	
	
	@Autowired
	private UserDao userDao;
    
	 /**
     * 生成session
     */
	 public Session initSession(String username){
		 
		 Session session = new Session();
		 //首先去数据库查询出用户的id
		 String userid = userDao.selectUserId(username);
		 String sessionid = UUID.randomUUID().toString();
		 session.setUserid(userid);
		 session.setUsername(username);
		 session.setSessionid(sessionid);
		 return session;
	 }
	 /**
     * 存放session
     */	
	 public void setSession(Session session){
		 //存放在redis公共缓存中，0号数据库,采用hash格式：key field value,目前设计key为userid
		 Map sessionInfo = new HashMap();
		 sessionInfo.put("username", session.getUsername());
		 sessionInfo.put("sessionid", session.getSessionid());
		 redisService.hmset("user:"+session.getUserid(),sessionInfo);
		 redisService.expire("user:"+session.getUserid(), 900);//设置初始过期时间为15分钟
	 }
	 /**
     * 验证session
     */	
	 public boolean checkSession(Session session){
		 String sessionid = redisService.hget("user:"+session.getUserid(), "sessionid");
		 if(sessionid!=null&&sessionid.equals(session.getSessionid())){
			 //延长过期时间
			 redisService.expire("user:"+session.getUserid(), 900);//设置初始过期时间为15分钟
			 return true;
		 }
		 return false;
	 }
}
