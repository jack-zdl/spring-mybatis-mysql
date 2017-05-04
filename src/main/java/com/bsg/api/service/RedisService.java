package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 */
@Service()
public class RedisService extends BaseService {
    

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
