package com.bsg.api.controller;

import com.bsg.api.exception.APIException;
import com.bsg.api.service.LogService;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhang on 2017/5/2.
 */
@Controller
@RequestMapping("v1.0/log")
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RespJson listAll(HttpServletRequest request, String type) throws APIException {
        RespJson respJson = null;
        try {
            respJson = logService.list(request, type);
        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }

    //关键字查询
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public RespJson list(HttpServletRequest request, @PathVariable("type") String type) throws APIException {
        RespJson respJson = null;
        try {
            respJson = logService.list(request, type);
        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }
}
