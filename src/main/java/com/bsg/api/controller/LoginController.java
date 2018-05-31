package com.bsg.api.controller;

import com.bsg.api.service.LoginService;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 */

@Controller
@RequestMapping("/v1.0")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * @param request
     * @param param
     * @return
     * @description 登录控制层
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public RespJson login(HttpServletRequest request, @RequestBody Map<String, Object> param) {
        RespJson respJson = null;
        System.out.println("登录进来：");
        try {
            respJson = loginService.login(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure(e.getMessage());
        }finally {
            return respJson;
        }
    }

    /**
     * @param request
     * @param param
     * @return
     * @descriptin 通过get参数的形式来传入参数 /param?key1=value&key2=value2
     */
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    @ResponseBody
    public RespJson loginByGet(HttpServletRequest request, @RequestBody Map<String, Object> param) {
        RespJson respJson = null;

        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @description
     */
    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    @ResponseBody
    public RespJson loginByUrl(HttpServletRequest request, @PathVariable String param) {
        RespJson respJson = null;
        return respJson;
    }


}
