package com.omen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lihaoming
 * @Date: 2020/6/23 9:16
 */
@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key) {
        Jedis jedis = null;
        String value;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 释放连接
     */
    private static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
