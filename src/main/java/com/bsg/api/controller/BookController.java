package com.bsg.api.controller;

import com.bsg.api.service.BookServiceImpl;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 */
@Controller
@RequestMapping("v1.0/book")
public class BookController {

    @Resource
    private BookServiceImpl bookService;

    @RequestMapping("/update")
    @ResponseBody
    public RespJson updateBook(HttpServletRequest request, @RequestBody Map<String,Object> param){
        RespJson respJson = null;
        try{
            respJson = bookService.updateBook();
        }catch (Exception e){
            e.printStackTrace();
        }
        return respJson;
    }
}
