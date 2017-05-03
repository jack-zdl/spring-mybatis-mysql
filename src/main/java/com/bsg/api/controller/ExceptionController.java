package com.bsg.api.controller;

import com.bsg.api.exception.APIException;
import com.bsg.api.exception.BookNotFoundException;
import com.bsg.api.exception.ServerException;
import com.bsg.api.service.CurdBookService;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/4/21.
 */
@Controller
@RequestMapping("v1.0/exception")
public class ExceptionController {
    @Resource
    private CurdBookService curdBookService;

    /**
     * @return
     * @description 404错误异常
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
            throw new BookNotFoundException();
        }

    }

    /**
     * @return
     * @description 505错误
     */
    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public RespJson fiveExceptin() {
        RespJson respJson = null;
        try {
            if (respJson == null) {
                throw new APIException();
            }
        } catch (Exception e) {
            throw new ServerException();
        }
        return respJson;
    }
}
