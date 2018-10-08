package com.magicabc.magicabcapp.util;

import com.magicabc.magicabcapp.bean.Club;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Date;
import java.util.List;

public class RedisUtil {

    private static JedisPool pool = JedisPoolSingle.INSTANCE.getInstance();
    public static void main(String[] args){
//        eval();
        info();
    }

    private void singleJedis(){
        Jedis jedis = pool.getResource();
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        String key = "club:1";
        Club club = new Club(1,"AC","米兰",new Date(),1);
        byte[] clubBytes = protostuffSerializer.serialize(club);
        jedis.set(key.getBytes(),clubBytes);

        byte[] resultBytes = jedis.get(key.getBytes());
        Club resultClub = protostuffSerializer.deserialize(resultBytes);
    }

    public static void info(){
        Jedis jedis = pool.getResource();
        try{
//            String infos = jedis.info();
            String infos = jedis.info("Server");
            System.out.println(infos);
        }catch (Exception e) {

        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }
    public void mdel(List<String> keys){
        Jedis jedis = pool.getResource();
        Pipeline pipeline = jedis.pipelined();
        for (String key:keys){
            pipeline.del(key);
        }
        pipeline.sync();
    }

    public static void eval(){
        Jedis jedis = pool.getResource();
        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Object result = jedis.eval(script,1,key);
        System.out.print(result);
    }
}
