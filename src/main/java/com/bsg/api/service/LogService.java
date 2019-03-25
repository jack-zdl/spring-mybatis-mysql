package com.bsg.api.service;

import com.bsg.api.dao.OperatelogDao;
import com.bsg.api.entity.OperatelogEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2017/5/2.
 */
@Service
public class LogService {
//    private Logger logger = Logger.getLogger(LogService.class);
    @Resource
    private OperatelogDao operatelogDao;

    public RespJson list(HttpServletRequest request, String type) throws APIException {
        RespJson respJson = null;
        List<OperatelogEntity> operatelogEntityList = new ArrayList<OperatelogEntity>();
        Map<String, Object> typeMap = new HashMap<String, Object>();
        try {
            typeMap.put("type", type);
            operatelogEntityList = operatelogDao.list(typeMap);
            respJson = RespJsonFactory.buildSuccess(operatelogEntityList);
        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }
}
