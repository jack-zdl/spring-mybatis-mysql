package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Service()
public class RedisTemplateService extends BaseService {

    //    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/spring-redis.xml");
//    RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
//    StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) ctx.getBean("stringRedisTemplate");
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
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
            for (String key : param.keySet()) {
                redisTemplate.opsForValue().set(key, param.get(key));
            }
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
