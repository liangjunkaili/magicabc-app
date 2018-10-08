package com.magicabc.magicabcapp.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public enum JedisPoolSingle {
    INSTANCE;
    public JedisPool getInstance(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL*5);
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE*3);
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE*2);
        poolConfig.setJmxEnabled(true);
        poolConfig.setMaxWaitMillis(3000);
        JedisPool jedisPool = new JedisPool(poolConfig,"127.0.0.1",6379);
        return jedisPool;
    }
}
