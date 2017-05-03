package com.bsg.api.controller;

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
 * Created by zhang on 2017/4/21. 提供接口查询应用状态，监测当压力测试时，应用的实时状态
 */
@Controller
@RequestMapping("v1.0/select")
public class SelectController {
    @Resource
    private LogService logService;

    /**
     * @param request
     * @return
     * @description 根据类型，来查询用户对软件进行了多少次操作
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public RespJson list(HttpServletRequest request, @PathVariable String type) {
        RespJson respJson = null;
        try {
            respJson = logService.list(request, type);
        } catch (Exception e) {
        }
        return respJson;
    }

    /**
     * @param request
     * @param type
     * @return
     * @description 查询软件对数据库进行多少次操作
     */
    @RequestMapping(value = "mysql/{type}", method = RequestMethod.GET)
    @ResponseBody
    public RespJson mysqlSelect(HttpServletRequest request, @PathVariable String type) {
        RespJson respJson = null;
        try {

        } catch (Exception e) {
        }
        return respJson;
    }
}
