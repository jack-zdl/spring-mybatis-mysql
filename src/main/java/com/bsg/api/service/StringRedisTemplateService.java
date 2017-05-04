package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Service()
public class StringRedisTemplateService extends BaseService {

    //    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/spring-redis.xml");
//    StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) ctx.getBean("stringRedisTemplate");
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
        return null;
    }

    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
            for (String key : param.keySet()) {
                System.out.println("key=" + key + "value=" + (String) param.get(key));
                vop.set(key, (String) param.get(key));
            }
            // vop.set("aaaaa", "bbbbb");
            respJson = RespJsonFactory.buildSuccess("stringRedisTemplate新增成功！");
        } catch (Exception e) {
            throw new APIException();
        }
//        String value = vop.get(key);
//        Assert.assertEquals(v, value);
        return respJson;
    }

    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson remove(HttpServletRequest request, String id) throws APIException {
        return null;
    }
}
