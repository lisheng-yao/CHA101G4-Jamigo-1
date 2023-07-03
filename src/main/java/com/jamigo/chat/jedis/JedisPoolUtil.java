package com.jamigo.chat.jedis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PreDestroy;
import java.time.Duration;

@Component
public class JedisPoolUtil {
    public static JedisPool jedisPool = null;

    private JedisPoolUtil(){

    }

    public static JedisPool getJedisPool(){
        if(jedisPool == null){
            synchronized (JedisPool.class){
                if(jedisPool == null){
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(10);
                    jedisPoolConfig.setMaxIdle(10);
                    jedisPoolConfig.setMaxWait(Duration.ofSeconds(30)); //設定等待時間
                }
            }
        }
        return jedisPool;
    }

    @PreDestroy
    public static void shutdownJedisPool(){
        if(jedisPool != null){
            jedisPool.destroy();
        }
    }
}
