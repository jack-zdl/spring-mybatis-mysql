package com.bsg.api.service;

import com.bsg.api.dao.OperatelogDao;
import com.bsg.api.entity.Operatelog;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.DateUtil;
import com.bsg.api.util.RespJson;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhang on 2017/3/29.
 * @description 提前定义一些经常使用的方法 1 填写日志的方法
 */
public abstract class BaseService {

    @Resource
    private OperatelogDao operatelogDao;
    /**
     * @description 定义写入日志表的方法
     *                 1将操作的情况写入日志
     */
    public void saveOperateLog(String page, String type, String user, String description)throws APIException{
        Date date = DateUtil.getCurrentDateTime();
        Operatelog operatelog = new Operatelog();
        operatelog.setOperatePage(page);
        operatelog.setOperateType(type);
        operatelog.setOperator(user);
        operatelog.setOperateTime(date);
        operatelog.setOperateDesc(description);
        operatelogDao.save(operatelog);
    }

    /**
     * @description 定义修改日志的方法，不确定有多少的参数，就将所有的参数写入，调用方法时可以使用null顶替
     * @param page
     * @param type
     * @param user
     * @param description
     */
    public void updateOperateLog(String page,String type,String user,String description)throws APIException{
        Date date = DateUtil.getCurrentDateTime();
        Operatelog operatelog = new Operatelog();
        operatelog.setOperatePage(page);
        operatelog.setOperateType(type);
        operatelog.setOperator(user);
        operatelog.setOperateTime(date);
        operatelog.setOperateDesc(description);
        operatelogDao.save(operatelog);
    }

    /**
     * @description 根据code的类型和code来查寻数据库字典获得string字符串
     * @param codeType
     * @param code
     * @return
     */
    public String getTextByCode(String codeType,String code){
        return null;
    }
    /**
     * @description 获取列表
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    public abstract RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException;

    /**
     * @description 获取单个对象
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    public abstract RespJson get(HttpServletRequest request, Map<String, Object> param)throws APIException;
    /**
     *@description 新增
     * @return
     */
    public abstract RespJson save(HttpServletRequest request, Map<String, Object> param)throws APIException;

    /**
     * @description 更新
     * @return
     */
    public abstract RespJson update(HttpServletRequest request, Map<String, Object> param)throws APIException;

    /**
     * @description 删除
     * @return
     */
    public abstract RespJson remove(HttpServletRequest request, Map<String, Object> param)throws APIException;
}
