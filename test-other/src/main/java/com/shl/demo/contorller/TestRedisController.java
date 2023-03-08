package com.shl.demo.contorller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * redis-springboot 缓存测试控制器
 */
public class TestRedisController {

    /**
     * 查询用户信息，并缓存
     */
    @Cacheable(value = "oneDay", key = "'getCacheBeanByUserId:' + #userId + ':' + #orgId", condition = "#userId!=null", unless = "#result==null")
    public Object getCacheBeanByUserId(String userId, String orgId) {
        return "0";
    }

    /**
     * 清除用户缓存
     */
    @CacheEvict(value = "oneDay", key = "'getCacheBeanByUserId:' + #userId + ':' + #orgId")
    public void deleteCacheUserCode(String userId, String orgId) {

    }
}
