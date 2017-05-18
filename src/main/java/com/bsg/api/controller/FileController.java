package com.bsg.api.controller;

import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhang on 2017/4/21. spring mvc restful的风格的文件上传下载
 */
@Controller
@RequestMapping("/v1.0/file")
public class FileController {
    /**
     * @return
     * @description 上传文件 restful风格没有前端
     */


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public RespJson fileUpload(HttpServletRequest request, @RequestParam("fileUpload") CommonsMultipartFile file) {
        RespJson respJson = null;
        if (!file.isEmpty()) {
            System.out.println("文件存在");
        }
        System.out.println("文件不存在");
        return respJson;
    }

    /**
     * @return
     * @description 下载文件
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public RespJson fileDownload() {
        RespJson respJson = null;
        return respJson;
    }
}
