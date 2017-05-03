package com.bsg.api.controller;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by zhang on 2017/4/21. spring的文件上传下载
 */
@Controller
@RequestMapping("V1.0/file")
public class FileController {
    /**
     * @return
     * @description 上传文件 第一种是基于流的方式上传文件 CommonsMultipartFile
     * @description 上传文件 第二种是基于spring的方式
     */


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RespJson fileUpload(HttpServletRequest request) throws IllegalStateException, IOException, APIException {
        RespJson respJson = null;
        try {
            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        String path = "C:/fumai-project/test/upload" + file.getOriginalFilename();
                        //上传
                        file.transferTo(new File(path));
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("上传文件错误");
        }

        return respJson;
    }

    /**
     * @return
     * @description 下载文件
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public RespJson fileDownload() {
        RespJson respJson = null;
        return respJson;
    }
}
