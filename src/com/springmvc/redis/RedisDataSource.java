package com.springmvc.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	public abstract ShardedJedis getRedisClient();//取得redis的客户端，可以执行命令了
    public void returnResource(ShardedJedis shardedJedis);//将资源返还给pool
    public void returnResource(ShardedJedis shardedJedis,boolean broken);//出现异常后，将资源返还给pool
}
