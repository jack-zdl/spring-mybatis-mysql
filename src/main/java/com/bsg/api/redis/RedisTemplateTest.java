package com.bsg.api.redis;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by zhang on 2017/5/2.
 *
 * @description 测试redisTemplate
 */
public class RedisTemplateTest {
    // 测试RedisTemplate,自主处理key的可读性(String序列号)
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/spring-redis.xml");
    RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
    StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) ctx.getBean("stringRedisTemplate");

    @Ignore
    @Test
    public void test4() {
        String key = "spring";
        ListOperations<String, String> lop = redisTemplate.opsForList();
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        // rt.setDefaultSerializer(serializer);

        lop.leftPush(key, "aaa");
        lop.leftPush(key, "bbb");
        long size = lop.size(key); // rt.boundListOps(key).size();
        Assert.assertEquals(2, size);
    }

    // 测试便捷对象StringRedisTemplate
    @Ignore
    @Test
    public void test5() {
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
        String key = "string_redis_template";
        String v = "use StringRedisTemplate set k v";
        vop.set(key, v);
        String value = vop.get(key);
        Assert.assertEquals(v, value);
    }
}
