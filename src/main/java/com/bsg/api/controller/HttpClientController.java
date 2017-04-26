package com.bsg.api.controller;

import com.bsg.api.service.HttpClientService;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/4/20. http接口 get post put delete
 */
@Controller
@RequestMapping("v1.0/http")
public class HttpClientController {
    @Resource
    private HttpClientService httpClientService;

    /**
     * @param request
     * @param param
     * @return
     * @description value="/get"
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RespJson getHttpClient(HttpServletRequest request, @RequestParam Map<String, Object> param) {
        RespJson respJson = null;
        try {
            respJson = httpClientService.getHttpClient(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("httpClientGet异常");
        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @description 
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RespJson postHttpClient(HttpServletRequest request, @RequestBody Map<String, Object> param) {
        RespJson respJson = null;
        return respJson;
    }
}
