package com.zx.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * RedisCache工具类
 *
 * @author ning
 * @create 2018-06-13 14:15
 **/
@Component("redisCache")
public class RedisCacheUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 从redis的String中取值
     * @param key
     * @return
     */
    public String sGet(String key) {
        if(key == null || "".equals(key)){
            return "key is null";
        }
      return  redisTemplate.opsForValue().get(key);
    }

    /**
     * 向redis的String中存放value
     * @param key
     * @param value
     */
    public void sSet(String key, String value, Long time, TimeUnit timeUnit) {
        if(key == null || "".equals(key)){
            return ;
        }
        if (time != null && time > 0){
            redisTemplate.opsForValue().set(key,value,time,timeUnit);
        }else {
            redisTemplate.opsForValue().set(key,value);
        }
    }


    /**
     * 向Hash中添加值
     * @param key      可以对应数据库中的表名
     * @param field    可以对应数据库表中的唯一索引
     * @param value    存入redis中的值
     */
    public void hset(String key, String field, String value) {
        if(key == null || "".equals(key)){
            return ;
        }
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 从redis中取出值
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field){
        if(key == null || "".equals(key)){
            return null;
        }
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 判断 是否存在 key 以及 hash key
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key, String field){
        if(key == null || "".equals(key)){
            return false;
        }
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 查询 key中对应多少条数据
     * @param key
     * @return
     */
    public long hsize(String key) {
        if(key == null || "".equals(key)){
            return 0L;
        }
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 删除
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        if(key == null || "".equals(key)){
            return;
        }
        redisTemplate.opsForHash().delete(key, field);
    }
}