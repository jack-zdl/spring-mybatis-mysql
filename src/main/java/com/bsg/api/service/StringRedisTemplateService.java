package com.bsg.api.service;

import com.alibaba.druid.util.StringUtils;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.KeyUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Service()
public class StringRedisTemplateService extends BaseService {

    public static int  i = 0;
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
                i++;
                System.out.println("key=" + key + "value=" + (String) param.get(key));
                vop.set(key, "="+i);
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

    private static final int TIMEOUT= 10*1000;



    //redis 分布式锁
    @Transactional(rollbackFor = Exception.class)
    public void orderProductMockDiffUser(String productId) throws Exception {
        long time = System.currentTimeMillis()+TIMEOUT;
        if(!lock(productId,String.valueOf(time))){
            throw new Exception("换个姿势再试试");
        }
        ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
        //2.下单
//        orders.put(KeyUtil.genUniqueKey(),productId);//生成随机用户id模拟高并发
//        sotckNum = stockNum-1;
//        try{
//            Thread.sleep(100);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        vop.setIfAbsent(productId,stockNum);
        unlock(productId,String.valueOf(time));
    }


    /***
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return 锁住返回true
     */
    public boolean lock(String key,String value){
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){//setNX 返回boolean
            return true;
        }
        //如果锁超时 ***
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间
            String oldvalue  = stringRedisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldvalue)&&oldvalue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }
    /***
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public void unlock(String key,String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            System.out.println("解锁异常");
        }
    }

}
