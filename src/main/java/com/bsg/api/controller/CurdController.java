package com.bsg.api.controller;

import com.bsg.api.exception.APIException;
import com.bsg.api.exception.BookNotFoundException;
import com.bsg.api.service.CurdBookService;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import com.bsg.api.vo.BookPostVo;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/4/21. 测试简单的数据库curd 对于书籍的简单的增删改查
 */
@RestController
@RequestMapping("/v1.0/crud")
public class CurdController {
    @Resource
    private CurdBookService curdBookService;

    /**
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RespJson save(HttpServletRequest request,  @RequestBody BookPostVo bookPostVo, Errors errors) throws APIException { // Map<String, Object> param

        if (errors.hasErrors()) {
            System.out.println("输入信息错误");
        }
        RespJson respJson = null;
        System.out.println("bookPostVo" + bookPostVo);
        System.out.println("bookPostVo验证=" + bookPostVo.toString());
        try {
            //respJson = curdBookService.save(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("书籍新增异常");
            throw new APIException("书籍新增异常");
        }
        return respJson;
    }

    /**
     * @param request
     * @param bookId
     * @return
     * @description 删除
     */
    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    @ResponseBody
    public RespJson remove(HttpServletRequest request, @PathVariable("bookId") String bookId) throws BookNotFoundException {
        RespJson respJson = null;
        try {
            respJson = curdBookService.remove(request, bookId);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("对应书籍不存在");
            throw new BookNotFoundException();
        }
        return respJson;
    }

    /**
     * @param request
     * @param bookId
     * @param param
     * @return
     * @description put请求修改可以在url中添加参数，也可以传递body体
     */
    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    @ResponseBody
    public RespJson update(HttpServletRequest request, @PathVariable("bookId") String bookId, @RequestBody Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            param.put("id", bookId);
            respJson = curdBookService.update(request, param);
            return respJson;
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("更新书籍失败");
            throw new BookNotFoundException("No Found Book");
        }

    }

    /**
     * @param request
     * @param param
     * @return
     * @description 无论是全部查询还是单个查询都可以使用的get接口可以再url后面添加？ key=value&key1=value1对应的param RequestParam 对应的是get请求的参数
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RespJson list(HttpServletRequest request, @RequestParam Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            respJson = curdBookService.list(request, param);
        } catch (Exception e) {
            respJson = RespJsonFactory.buildFailure("书籍查询异常");
            throw new APIException("书籍查询异常");
        }
        return respJson;
    }


}
