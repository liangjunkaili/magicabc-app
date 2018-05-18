package com.magicabc.magicabcapp.util;

import com.magicabc.magicabcapp.bean.Club;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Date;
import java.util.List;

public class RedisUtil {
    public static void main(String[] args){
        eval();
    }

    private void singleJedis(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("liangjun");
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        String key = "club:1";
        Club club = new Club(1,"AC","米兰",new Date(),1);
        byte[] clubBytes = protostuffSerializer.serialize(club);
        jedis.set(key.getBytes(),clubBytes);

        byte[] resultBytes = jedis.get(key.getBytes());
        Club resultClub = protostuffSerializer.deserialize(resultBytes);
        /*System.out.print(jedis.set("hello","world"));
        System.out.print(jedis.incr("counter"));
        System.out.print(jedis.get("hello"));
        System.out.print(jedis.hset("myhash","f1","v1"));
        System.out.print(jedis.hset("myhash","f2","v2"));
        System.out.print(jedis.hgetAll("myhash"));
        System.out.print(jedis.rpush("mylist","1"));
        System.out.print(jedis.rpush("mylist","2"));
        System.out.print(jedis.rpush("mylist","3"));
        System.out.print(jedis.lrange("mylist",0,-1));
        System.out.print(jedis.sadd("myset","a"));
        System.out.print(jedis.sadd("myset","b"));
        System.out.print(jedis.sadd("myset","c"));
        System.out.print(jedis.smembers("myset"));
        System.out.print(jedis.zadd("myzset",99,"tom"));
        System.out.print(jedis.zadd("myzset",66,"peter"));
        System.out.print(jedis.zadd("myzset",33,"james"));
        System.out.print(jedis.zrangeWithScores("myzset",0,-1));*/
    }
    private void jedisPool(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL*5);
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE*3);
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE*2);
        poolConfig.setJmxEnabled(true);
        poolConfig.setMaxWaitMillis(3000);
        JedisPool jedisPool = new JedisPool(poolConfig,"127.0.0.1",6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.get("hello");
        }catch (Exception e){
            System.out.print(e.getMessage());
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }

    public void mdel(List<String> keys){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("liangjun");
        Pipeline pipeline = jedis.pipelined();
        for (String key:keys){
            pipeline.del(key);
        }
        pipeline.sync();
    }

    public static void eval(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("liangjun");
        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Object result = jedis.eval(script,1,key);
        System.out.print(result);
    }
}
