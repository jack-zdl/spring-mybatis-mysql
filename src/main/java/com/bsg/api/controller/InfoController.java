package com.bsg.api.controller;

import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhang on 2017/4/21. spring的异步消息
 */
@Controller
@RequestMapping("v1.0/info")
public class InfoController {
    @RequestMapping("/asyn")
    public RespJson asynInfo() {
        RespJson respJson = null;
        return respJson;
    }

    @RequestMapping("/syn")
    public RespJson synInfo() {
        RespJson respJson = null;
        return respJson;
    }
}
