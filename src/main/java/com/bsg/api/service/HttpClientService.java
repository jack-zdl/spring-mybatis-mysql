package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.HttpClientUtils;
import com.bsg.api.util.RespJson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/4/20.
 */
@Service()
public class HttpClientService {
    private static Logger logger = Logger.getLogger(HttpClientService.class);
    @Value("#{configProperties['method.get']}")
    private String methodGet;

    public RespJson getHttpClient(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            logger.info("测试信息=" + methodGet);
            respJson = HttpClientUtils.sendHttpGet(methodGet);
        } catch (Exception e) {
            logger.error("连接出错=" + e.getMessage());
            e.printStackTrace();
            throw new APIException("连接出错");
        }
        return respJson;
    }
}
