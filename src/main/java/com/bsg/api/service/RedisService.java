package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Service()
public class RedisService extends BaseService {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/spring-redis.xml");
    RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
    StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) ctx.getBean("stringRedisTemplate");

    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        return respJson;
    }

    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {

        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }

    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            String key = "redisKey";
            String value = "redisValue";
            redisTemplate.opsForValue().set(key, value);
            respJson = RespJsonFactory.buildSuccess("redis数据存储成功！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException();
        }
        return respJson;
    }

    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {

        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }

    @Override
    public RespJson remove(HttpServletRequest request, String id) throws APIException {
        RespJson respJson = null;
        try {

        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }
}
