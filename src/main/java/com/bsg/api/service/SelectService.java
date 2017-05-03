package com.bsg.api.service;

import com.bsg.api.util.RespJson;

/**
 * Created by zhang on 2017/5/2.
 *
 * @description 查询数据库日志记录 查询数据库本身的操作次数
 */
public class SelectService {


    /**
     * @return
     * @description 查询数据库
     * @description show global status where  Variable_name in ('Com_insert','Com_update','Com_delete','Com_select','open_tables')
     */
    public RespJson selectMysql() {
        RespJson respJson = null;
        return respJson;
    }
}
