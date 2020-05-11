package com.nancy.petshow.util;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chen
 * @date 2020/5/11 13:49
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ValueOperations<String, Object> valueOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = new HashSet<>(keys);
        redisTemplate.delete(kSet);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit 时间颗粒度
     *                 TimeUnit.DAYS          //天
     *                 TimeUnit.HOURS         //小时
     *                 TimeUnit.MINUTES       //分钟
     *                 TimeUnit.SECONDS       //秒
     *                 TimeUnit.MILLISECONDS  //毫秒
     * @return
     */
    public Long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 指定key的生命周期
     *
     * @param key
     * @param timeOut
     * @param timeUnit
     */
    public void setKeyExpire(String key, long timeOut, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * StringSet
     *
     * @param key
     * @param value
     */
    public void setString(String key, Object value) {
        valueOperations.set(key, value);
    }

    /**
     * StringSet 并设置过期时间
     *
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     */
    public void setString(String key, Object value, long timeOut, TimeUnit timeUnit) {
        valueOperations.set(key, value, timeOut, timeUnit);
    }

    /**
     * StringGet
     *
     * @param key
     * @return
     */
    public Object getString(String key) {
        return valueOperations.get(key);
    }

    /**
     * StringIncr
     *
     * @param key
     * @return
     */
    public Long incrString(String key, long delta) {
        if (delta < 0) {
            return null;
        }
        return valueOperations.increment(key, delta);
    }

    /**
     * StringDecr
     *
     * @param key
     * @return
     */
    public Long decrString(String key, long delta) {
        if (delta < 0) {
            return null;
        }
        return valueOperations.increment(key, -delta);
    }

    /**
     * HashGet
     *
     * @param key
     * @param item
     * @return
     */
    public Object getHash(String key, String item) {
        return hashOperations.get(key, item);
    }

    /**
     * HashSet
     *
     * @param key
     * @param item
     * @param value
     * @return
     */
    public void setHash(String key, String item, Object value) {
        hashOperations.put(key, item, value);
    }

    /**
     * HashSet 并设置过期时间
     *
     * @param key
     * @param item
     * @param value
     * @param timeOut
     * @param timeUnit
     * @return
     */
    public void setHash(String key, String item, Object value, long timeOut, TimeUnit timeUnit) {
        hashOperations.put(key, item, value);
        setKeyExpire(key, timeOut, timeUnit);
    }

    /**
     * HashMapGet
     *
     * @param key
     * @return
     */
    public Map<String, Object> getHashMap(String key) {
        return hashOperations.entries(key);
    }

    /**
     * HashMapSet
     *
     * @param key
     * @param map
     * @return
     */
    public void setHashMap(String key, Map<String, Object> map) {
        hashOperations.putAll(key, map);
    }

    /**
     * HashMapSet 并设置过期时间
     *
     * @param key
     * @param map
     * @param timeOut
     * @param timeUnit
     * @return
     */
    public void setHashMap(String key, Map<String, Object> map, long timeOut, TimeUnit timeUnit) {
        hashOperations.putAll(key, map);
        setKeyExpire(key, timeOut, timeUnit);
    }

    /**
     * HashDel
     *
     * @param key
     * @param items
     */
    public void delHashItem(String key, String... items) {
        hashOperations.delete(key, items);
    }

    /**
     * 判断hash表中是否有该项
     *
     * @param key
     * @param item
     * @return
     */
    public Boolean hasHashItem(String key, String item) {
        return hashOperations.hasKey(key, item);
    }

    /**
     * 判断set中是否存在该值
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean hasSetValue(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    /**
     * SetGet
     *
     * @param key
     * @return
     */
    public Set<Object> getSet(String key) {
        return setOperations.members(key);
    }

    /**
     * SetSet
     *
     * @param key
     * @param values
     * @return
     */
    public Long setSet(String key, Object... values) {
        return setOperations.add(key, values);
    }

    /**
     * SetSet 并设置过期时间
     *
     * @param key
     * @param timeOut
     * @param timeUnit
     * @param values
     * @return
     */
    public void setSet(String key, long timeOut, TimeUnit timeUnit, Object... values) {
        setOperations.add(key, values);
        setKeyExpire(key, timeOut, timeUnit);
    }

    /**
     * 获取set数据长度
     *
     * @param key
     * @return
     */
    public Long getSetSize(String key) {
        return setOperations.size(key);
    }

    /**
     * 删除set中的value
     *
     * @param key
     * @param values
     * @return
     */
    public void delSetValue(String key, Object... values) {
        setOperations.remove(key, values);
    }

    /**
     * ListGet
     *
     * @param key
     * @param start 开始
     * @param end   结束  0到-1代表所有值
     * @return
     */
    public List<Object> getList(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    /**
     * 获取list数据长度
     *
     * @param key
     * @return
     */
    public Long getListSize(String key) {
        return listOperations.size(key);
    }

    /**
     * 通过索引获取list中的值
     *
     * @param key
     * @param index 索引  0表头,-1表尾
     * @return
     */
    public Object getListByIndex(String key, long index) {
        return listOperations.index(key, index);
    }

    /**
     * 队列弹出
     *
     * @param key
     * @return
     */
    public Object getListQueue(String key) {
        return listOperations.leftPop(key);
    }

    /**
     * 栈弹出
     *
     * @param key
     * @return
     */
    public Object getListStack(String key) {
        return listOperations.rightPop(key);
    }

    /**
     * ListSet
     *
     * @param key
     * @param value
     * @return
     */
    public void setList(String key, Object value) {
        listOperations.rightPush(key, value);
    }

    /**
     * ListSet 并设置过期时间
     *
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     * @return
     */
    public void setList(String key, Object value, long timeOut, TimeUnit timeUnit) {
        listOperations.rightPush(key, value);
        setKeyExpire(key, timeOut, timeUnit);
    }

    /**
     * ListSet 存入list集合
     *
     * @param key
     * @param value
     * @return
     */
    public void setList(String key, List<Object> value) {
        listOperations.rightPushAll(key, value);
    }

    /**
     * ListSet 存入list集合 并设置过期时间
     *
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     * @return
     */
    public void setList(String key, List<Object> value, long timeOut, TimeUnit timeUnit) {
        listOperations.rightPushAll(key, value);
        setKeyExpire(key, timeOut, timeUnit);
    }

    /**
     * 通过索引修改list中的值
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    public void setListByIndex(String key, long index, Object value) {
        listOperations.set(key, index, value);
    }

    /**
     * 移除list中N个为value的值
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public void delListValue(String key, long count, Object value) {
        listOperations.remove(key, count, value);
    }
}
