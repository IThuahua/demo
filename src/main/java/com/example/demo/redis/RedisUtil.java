package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.*;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhoushenghua on
 */
public class RedisUtil {

    @Autowired()
    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOperations;
    private ListOperations<String, Object> listOperations;
    private SetOperations<String, Object> setOperations;
    private ZSetOperations<String,Object> zSetOperations;
    private HashOperations<String, Object, Object> hashOperations;

    {
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
        setOperations = redisTemplate.opsForSet();
        zSetOperations = redisTemplate.opsForZSet();
        hashOperations = redisTemplate.opsForHash();
    }

    /**
     * 指定缓存失效时间
     */
    public boolean expire(String key, long time){
        try {
            if(time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 依据key获取过期时间
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     */
    public void del(String... key){
        if(key != null && key.length > 0) {
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }





    //Strings
    public Object get(String key){
        return key == null ? null:valueOperations.get(key);
    }

    public boolean set(String key, Object value){
        try{
            valueOperations.set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, long time){
        try{
            if(time > 0){
                valueOperations.set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long incr(String key, long incrNum){
        if(incrNum < 0) throw new RuntimeException("低增值必须大于0");
        return valueOperations.increment(key, incrNum);
    }

    public long decr(String key, long decrNum){
        if(decrNum < 0) throw new RuntimeException("低减值必须大于0");
        return valueOperations.decrement(key, -decrNum);
    }





    //lists
    public List<Object> lGet(String key, long start, long end){
        try{
            return listOperations.range(key, start, end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public long lGetListSize(String key){
        try{
            return listOperations.size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public Object lGetIndex(String key, long index){
        try{
            return listOperations.index(key, index);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean lSet(String key, Object value){
        try{
            listOperations.rightPush(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, Object value, Long time){
        try{
            listOperations.rightPush(key, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value){
        try{
            listOperations.rightPushAll(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value, long time){
        try{
            listOperations.rightPushAll(key, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean lUpdateIndex(String key,long index, Object value){
        try{
            listOperations.set(key,index,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long lRemove(String key, long count, Object value){
        try{
            long remove = listOperations.remove(key, count, value);
            return remove;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }





    //sets
    public Set<Object> sGet(String key){
        try{
            return setOperations.members(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean sHasKey(String key, Object value){
        try{
            return setOperations.isMember(key, value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long sSet(String key, Object... values){
        try{
            return setOperations.add(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long sSetAndTime(String key, long time, Object... values){
        try{
            Long count = setOperations.add(key, values);
            if(time > 0){
                expire(key, time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long sGetSetSize(String key){
        try{
            return setOperations.size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long setRemove(String key, Object... values){
        try{
            Long count = setOperations.remove(key,values);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }





    //hashes
    public Object hGet(String key, String item){
        return hashOperations.get(key, item);
    }

    public boolean hset(String key, String item, Object value){
        try{
            hashOperations.put(key, item, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, Object value, long time){
        try{
            hashOperations.put(key, item, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Map<Object, Object> hmget(String key){
        return hashOperations.entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map){
        try{
            hashOperations.putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> map, long time){
        try{
            hashOperations.putAll(key, map);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void hdel(String key, Object... item){
        hashOperations.delete(key, item);
    }

    public boolean hHasKey(String key, String item){
        return hashOperations.hasKey(key, item);
    }

    public double hincr(String key, String item, double by){
        return hashOperations.increment(key, item, by);
    }

    public double hdecr(String key, String item, double by){
        return hashOperations.increment(key, item, -by);
    }





}
