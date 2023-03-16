package com.shl.demo.utils;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jyh
 * @version 1.0
 * @date 2020/2/11 12:59
 */
@Service
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    //ukey 口令
    public final String XSD_INVOICE_CODE_PWD = "XSD:INVOICE:CODE:PWD:";

    //活跃用户数量统计
    public final String XSD_LOG_ACTIVE_COUNT_COMPANY = "XSD:USER:ACTIVE:COUNT_COMPANY";
    public final String XSD_LOG_ACTIVE_COUNT_PHONE = "XSD:USER:ACTIVE:COUNT_PHONE";

    //ukey 开票限额
    public final String XSD_INVOICE_CODE_PRICE = "XSD:INVOICE:CODE:PRICE:";

    //用户盒子使用4g  每周提醒一次请及时链接wifi
    public final String XSD_WIFI_HINT = "XSD:WIFI:HINT:";

    /**
     * 写入缓存
     *
     * @param key   key
     * @param value 值
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key   key
     * @param value 值
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 数据自增
     *
     * @param key
     * @return
     */
    public boolean incr(final String key) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.increment(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 过期设置
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPush(k, v);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void rPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     * @param timeout
     * @param unit
     */
    public void rPush(String k, Object v, long timeout, TimeUnit unit) {
        rPush(k, v);

        redisTemplate.expire(k, timeout, unit);
    }

    /*
     * @ Description   :  向set中添加数据
     * @ Author        :  胡贵龙
     * @ CreateDate    :  2021-4-25 17:09
     * @ Parms    :
     * @ Return  :
     */
    public void opsForSet(String k, Object v, long timeout, TimeUnit unit) {
        redisTemplate.opsForSet().add(k, v);
        redisTemplate.expire(k, timeout, unit);
    }

    /*
     * @ Description   :  向set中添加数据
     * @ Author        :  胡贵龙
     * @ CreateDate    :  2021-4-25 17:09
     * @ Parms    :
     * @ Return  :
     */
    public Boolean isNumber(String k, Object v) {
        return redisTemplate.opsForSet().isMember(k, v);

    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 获取列表长度
     *
     * @param k
     * @return
     */

    public Long lLen(String k) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.size(k);
    }

    /**
     * 集合添加set
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合添加set(设置过期时间)
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void add(String key, Object value, long timeout, TimeUnit unit) {
        add(key, value);
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 集合获取set
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 集合成员数获取set
     *
     * @param key
     * @return
     */
    public Long scard(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.size(key);
    }

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    public long sDel(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }


    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    public void zDel(String key, Object value) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.remove(key, value);
    }

    public Long zReverseRank(String key, Object value) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        Long aLong = zset.reverseRank(key, value);
        return aLong;
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    public Set<ZSetOperations.TypedTuple<Object>> zRange(String key) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeWithScores(key, 0, -1);
    }

    public boolean ifAbsent(Class<?> clazz, String name) {
        return !redisTemplate.opsForValue().setIfAbsent(clazz.getName() + ":execute:"+name, "lock", Duration.ofSeconds(30));
    }
}