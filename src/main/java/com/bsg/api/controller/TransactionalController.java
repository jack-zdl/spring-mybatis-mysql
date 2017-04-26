package com.bsg.api.controller;

import com.bsg.api.service.CurdBookService;
import com.bsg.api.service.TransactionalService;
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
@RequestMapping("v1.0/book")
public class TransactionalController {
    @Resource
    private CurdBookService curdBookService;
    @Resource
    private TransactionalService transactionalService;

    /**
     * @param request
     * @param param
     * @return
     * @description 1获得所有的书籍 2查询某一个书籍的个数
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RespJson list(HttpServletRequest request, @RequestParam Map<String, Object> param) {
        RespJson respJson = null;
        try {
            respJson = curdBookService.list(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("查询书籍失败");
            System.out.print(e.getMessage());
        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @description 单个tx  1购买书籍，新增书籍信息，改变用户金额 简单事务操作 删除原来的书籍信息
     * @description 1个查询 查询该书籍是否还存在
     * @description 2个insert  插入日志记录 插入书籍信息
     * @description 1update  更新用户信息和书籍数量
     * @description 1delete 删除原先的书籍信息
     */
    @RequestMapping(value = "/update/oneTx", method = RequestMethod.POST)
    @ResponseBody
    public RespJson updateBookByOneTx(HttpServletRequest request, @RequestBody Map<String, Object> param) {
        RespJson respJson = null;
        try {
            respJson = transactionalService.updateBookByOneTx(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("购买书籍失败");
            e.printStackTrace();
        } finally {
            return respJson;
        }
    }

    /**
     * @param request
     * @param param
     * @return RespJson
     * @description 多个复杂tx 1购买书籍，改变书籍数量，改变用户金额 1 9个查询 2 insert 2 update 2remove
     * @description 9次查询 在每个表中查询一次  三个join查询 查询书籍表的创造者用户id 已经用户的角色 关联书籍表，用户表，角色表，授权表
     * @description 3insert 插入log日志 插入字典类型将书籍名称写入字典类型中 插入字典表
     * @description 2update 更新书籍信息 更新用户信息
     * @description 2remove 删除字典类型表信息 删除字典表
     */
    @RequestMapping(value = "/update/moreTx", method = RequestMethod.POST)
    @ResponseBody
    public RespJson updateBookByMoreTx(HttpServletRequest request, @RequestBody Map<String, Object> param) {
        RespJson respJson = null;
        try {
            respJson = transactionalService.updateBookByMoreTx(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("购买书籍失败");
            e.printStackTrace();
        } finally {
            return respJson;
        }
    }
}
