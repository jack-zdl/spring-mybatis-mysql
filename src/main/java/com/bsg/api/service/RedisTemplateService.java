package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.exception.RedisConnectException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
//import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Service()
public class RedisTemplateService extends BaseService {
//    private static Logger logger = Logger.getLogger(RedisTemplateService.class);
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        Object object = null;
        try {
            DataType type = redisTemplate.type(param.get("key"));
            if (DataType.NONE == type) {
                respJson = RespJsonFactory.buildFailure("没有查询到！");
                return respJson;
            } else if (DataType.STRING == type) {
                object = redisTemplate.opsForValue().get(param.get("key"));
            } else if (DataType.LIST == type) {
                object = redisTemplate.opsForList().range(param.get("key"), 0, -1);
            } else if (DataType.HASH == type) {
                object = redisTemplate.opsForHash().entries(param.get("key"));
            }
            respJson = RespJsonFactory.buildSuccess(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisConnectException();
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
            throw new RedisConnectException();
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
            redisTemplate.delete(id);
            respJson = RespJsonFactory.buildSuccess("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisConnectException();
        }
        return respJson;
    }
}
