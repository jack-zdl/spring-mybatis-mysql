package com.bsg.api.controller;

import com.bsg.api.exception.APIException;
import com.bsg.api.exception.RedisConnectException;
import com.bsg.api.service.StringRedisTemplateService;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Controller
@RequestMapping("v1.0/stringRedis")
public class RedisStringController {
    @Resource
    private StringRedisTemplateService stringRedisTemplateService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RespJson get(HttpServletRequest request, @RequestBody Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            respJson = stringRedisTemplateService.get(request, param);
        } catch (Exception e) {
            throw new RedisConnectException();
        }
        return respJson;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RespJson save(HttpServletRequest request, @RequestBody Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            respJson = stringRedisTemplateService.save(request, param);
        } catch (Exception e) {
            throw new RedisConnectException();
        }
        return respJson;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public RespJson update(HttpServletRequest request, @RequestBody Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            respJson = stringRedisTemplateService.update(request, param);
        } catch (Exception e) {
            throw new RedisConnectException();
        }
        return respJson;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public RespJson delete(HttpServletRequest request, @PathVariable String key) throws APIException {
        RespJson respJson = null;
        try {
            respJson = stringRedisTemplateService.remove(request, key);
        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }
}
