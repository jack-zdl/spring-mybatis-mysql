package com.bsg.api.service;

import com.alibaba.fastjson.JSONObject;
import com.bsg.api.constant.DictConstants;
import com.bsg.api.constant.DictTypeConstants;
import com.bsg.api.constant.SysConstants;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.HttpClientUtils;
import com.bsg.api.util.IPUtil;
import com.bsg.api.util.RespJson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhang on 2017/4/20.
 */
@Service()
public class HttpClientService extends BaseService {
    private static Logger logger = Logger.getLogger(HttpClientService.class);
    @Value("#{configProperties['method.get.url']}")
    private String methodGetUrl;
    @Value("#{configProperties['method.post.url']}")
    private String methodPostUrl;
    @Value("#{configProperties['method.put.url']}")
    private String methodPutUrl;
    @Value("#{configProperties['method.delete.url']}")
    private String methodDeleteUrl;

    @Transactional(rollbackFor = APIException.class)
    public RespJson getHttpClient(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        try {
            respJson = HttpClientUtils.sendHttpGet(methodGetUrl);
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.HTTP_GET, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.HTTP_GET, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("连接出错=" + e.getMessage());
            e.printStackTrace();
            throw new APIException("连接出错");
        }
        return respJson;
    }

    @Transactional(rollbackFor = APIException.class)
    public RespJson getHttpClientByPost(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        JSONObject jsonObject = new JSONObject();
        try {
            for (String key : param.keySet()) {
                jsonObject.put(key, param.get(key));
            }
            String stringParam = String.valueOf(jsonObject);
            respJson = HttpClientUtils.sendHttpPost(methodPostUrl, stringParam);
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.HTTP_POST, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.HTTP_POST, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("连接出错=" + e.getMessage());
            e.printStackTrace();
            throw new APIException("连接出错");
        }
        return respJson;
    }

    @Transactional(rollbackFor = APIException.class)
    public RespJson getHttpClientByPut(HttpServletRequest request, String id) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        try {
            respJson = HttpClientUtils.sendHttpPut(methodPutUrl);
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.HTTP_PUT, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.HTTP_PUT, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("连接出错=" + e.getMessage());
            e.printStackTrace();
            throw new APIException("连接出错");
        }
        return respJson;
    }

    @Transactional(rollbackFor = APIException.class)
    public RespJson getHttpClientByDelete(HttpServletRequest request, String id) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        try {
            respJson = HttpClientUtils.sendHttpDelete(methodDeleteUrl);
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.BUTTON_DELETE, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_HTTP, DictConstants.BUTTON_DELETE, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            e.printStackTrace();
            throw new APIException();
        }
        return respJson;
    }

    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson remove(HttpServletRequest request, String id) throws APIException {
        return null;
    }
}
