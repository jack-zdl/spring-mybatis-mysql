package com.bsg.api.controller;

import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhang on 2017/4/21. spring的文件上传下载
 */
@Controller
@RequestMapping("V1.0/file")
public class FileController {
    /**
     * @return
     * @description 上传文件
     */
    @RequestMapping("/upload")
    public RespJson fileUpload() {
        RespJson respJson = null;
        return respJson;
    }

    /**
     * @return
     * @description 下载文件
     */
    @RequestMapping("/download")
    public RespJson fileDownload() {
        RespJson respJson = null;
        return respJson;
    }
}
