package com.bsg.api.service;

import com.bsg.api.dao.OperatelogDao;
import com.bsg.api.entity.OperatelogEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.DateUtil;
import com.bsg.api.util.PrimaryKeyUtils;
import com.bsg.api.util.RespJson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhang on 2017/3/29.
 *
 * @description 提前定义一些经常使用的方法 1 填写日志的方法
 */
@Service()
public abstract class BaseService {

    private static Logger logger = Logger.getLogger(BookService.class);

    @Resource
    private OperatelogDao operatelogDao;

    private OperatelogEntity getOperateLog(String page, String type, String user, int status, String description) {
        Date date = DateUtil.getCurrentDateTime();
        OperatelogEntity operatelog = new OperatelogEntity();
        System.out.println(PrimaryKeyUtils.uniqueId());
        operatelog.setId(PrimaryKeyUtils.uniqueId());
        operatelog.setOperatePage(page);
        operatelog.setOperateType(type);
        operatelog.setOperator(user);
        operatelog.setOperateTime(date);
        operatelog.setStatus(status);
        operatelog.setOperateDesc(description);
        return operatelog;
    }

    /**
     * @description 定义写入日志表的方法 1将操作的情况写入日志
     */

    public void saveOperateLog(String page, String type, String user, int status, String description) throws APIException {
        try {
            OperatelogEntity operatelog = getOperateLog(page, type, user, status, description);
            operatelogDao.save(operatelog);
        } catch (Exception e) {
            logger.error("save日志出错" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * @param page
     * @param type
     * @param user
     * @param description
     * @description 定义修改日志的方法，不确定有多少的参数，就将所有的参数写入，调用方法时可以使用null顶替
     */
    public void updateOperateLog(String page, String type, String user, int status, String description) throws APIException {
        try {
            OperatelogEntity operatelog = getOperateLog(page, type, user, status, description);
            operatelogDao.save(operatelog);
        } catch (Exception e) {

        }

    }

    /**
     * @param codeType
     * @param code
     * @return
     * @description 根据code的类型和code来查寻数据库字典获得string字符串
     */
    public String getTextByCode(String codeType, String code) {
        return null;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 获取列表
     */
    public abstract RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException;

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 获取单个对象
     */
    public abstract RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException;

    /**
     * @return
     * @description 新增
     */
    public abstract RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException;

    /**
     * @return
     * @description 更新
     */
    public abstract RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException;

    /**
     * @return
     * @description 删除
     */
    public abstract RespJson remove(HttpServletRequest request, Map<String, Object> param) throws APIException;
}
