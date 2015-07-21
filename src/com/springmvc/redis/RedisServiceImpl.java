package com.springmvc.redis;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
@Service("redisService")
public class RedisServiceImpl implements RedisService {

	private static String redisCode = "utf-8";
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Override
	public long del(String... keys) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });		
	}

	@Override
	public void set(String key, String value, long liveTime) {
		// TODO Auto-generated method stub
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		this.set(key, value, 0L);
	}

	@Override
	public void set(byte[] key, byte[] value) {
		// TODO Auto-generated method stub
		this.set(key, value, 0L);
	}

	@Override
	public String get(final String key) {
		return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(key.getBytes()), redisCode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
	}

	@Override
	public Set keys(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String flushDB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long dbSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String ping() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hget(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}

	@Override
	public void hset(String key, String field, String value) {
		redisTemplate.opsForHash().put(key, field, value);	
	}

	@Override
	public boolean expire(String key, int seconds) {
		// TODO Auto-generated method stub
		return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	@Override
	public void hmset(String key, Map<String, String> hash) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().putAll(key, hash);
	}	

}
