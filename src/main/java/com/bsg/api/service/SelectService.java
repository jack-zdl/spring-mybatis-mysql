package com.bsg.api.service;

import com.bsg.api.dao.SelectDao;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2017/5/2.
 *
 * @description 查询数据库日志记录 查询数据库本身的操作次数
 */
@Service()
public class SelectService {
    @Resource
    private SelectDao selectDao;

    /**
     * @return
     * @description 查询数据库
     * @description show global status where  Variable_name in ('Com_insert','Com_update','Com_delete','Com_select','open_tables')
     */
    public RespJson selectMysql() throws APIException {
        RespJson respJson = null;
        List<Object> list = new ArrayList<Object>();
        try {
            list = selectDao.list();
            respJson = RespJsonFactory.buildSuccess(list);
        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }

    /**
     * @return
     * @description 查询数据库
     * @description show global status where  Variable_name in ('Com_insert','Com_update','Com_delete','Com_select','open_tables')
     */
    public RespJson selectMysql(HttpServletRequest request, String type) throws APIException {
        RespJson respJson = null;
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("type", type);
            list = selectDao.get(map);
            respJson = RespJsonFactory.buildSuccess(list);
        } catch (Exception e) {
            throw new APIException();
        }
        return respJson;
    }
}
