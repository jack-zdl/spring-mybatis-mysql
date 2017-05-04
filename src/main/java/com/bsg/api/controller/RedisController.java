package com.bsg.api.controller;

import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Controller
@RequestMapping("V1.0/redis")
public class RedisController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RespJson list(HttpServletRequest request, @RequestParam Map<String, Object> param) {
        RespJson respJson = null;
        return respJson;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RespJson save(HttpServletRequest request, @RequestParam Map<String, Object> param) {
        RespJson respJson = null;
        return respJson;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public RespJson update(HttpServletRequest request, @RequestParam Map<String, Object> param) {
        RespJson respJson = null;
        return respJson;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public RespJson delete(HttpServletRequest request, @PathVariable String key) {
        RespJson respJson = null;
        return respJson;
    }
}
