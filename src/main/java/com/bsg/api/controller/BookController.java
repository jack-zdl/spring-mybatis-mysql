package com.bsg.api.controller;

import com.bsg.api.service.BookService;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
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
    private BookService bookService;

    /**
     * @description 1获得所有的书籍 2查询某一个书籍的个数
     * @param request
     * @param param
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public RespJson list(HttpServletRequest request, @RequestBody Map<String,Object> param){
        RespJson respJson = null;
        try{
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return  respJson;
    }
    /**
     * @description  单个tx  1购买书籍，改变书籍数量，改变用户金额
     * @param request
     * @param param
     * @return
     */
    @RequestMapping("/update/oneTx")
    @ResponseBody
    public RespJson updateBookByOneTx(HttpServletRequest request, @RequestBody Map<String,Object> param) {
        RespJson respJson = null;
        try {
            respJson = bookService.updateBookByOneTx(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("购买书籍失败");
            e.printStackTrace();
        }finally {
            return respJson;
        }
    }
        /**
         * @description 多个tx 1购买书籍，改变书籍数量，改变用户金额
         * @param request
         * @param param
         * @return
         */
    @RequestMapping("/update/moreTx")
    @ResponseBody
    public RespJson updateBookByMoreTx(HttpServletRequest request, @RequestBody Map<String,Object> param){
        RespJson respJson = null;
        try{
            respJson = bookService.updateBookByMoreTx(request, param);
        }catch (Exception e){
            respJson = RespJsonFactory.buildFailure("购买书籍失败");
            e.printStackTrace();
        }finally {
            return respJson;
        }
    }
}
