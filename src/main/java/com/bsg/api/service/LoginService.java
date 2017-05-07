package com.bsg.api.service;

import com.bsg.api.constant.DictConstants;
import com.bsg.api.constant.DictTypeConstants;
import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.OperatelogDao;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.OperatelogEntity;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @description 登录服务层 Created by zhang on 2017/3/29.
 */

@Service("loginService")
public class LoginService {

    private static Logger logger = Logger.getLogger(LoginService.class);

    @Resource
    private UserDao userDao;
    @Resource
    private OperatelogDao operatelogDao;

    /**
     * @param request
     * @param param
     * @return
     * @description 登录方法
     */
    @Transactional(rollbackFor = APIException.class)
    public RespJson login(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        logger.error("user访问进来：" + param);
        UserEntity user = userDao.getUser(param);
        Date date = DateUtil.getCurrentDateTime();
        try {
            if (user == null) {
                logger.error("用户名密码错误");
                return RespJsonFactory.buildWarning("用户名密码错误");
            } else {
                if (!user.getValidate()) {
                    return RespJsonFactory.buildWarning("用户无权限登录");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute(SysConstants.SESSION_USER, user);

                    OperatelogEntity operatelog = new OperatelogEntity();
                    System.out.println(PrimaryKeyUtils.uniqueId());
                    operatelog.setId(PrimaryKeyUtils.uniqueId());
                    operatelog.setOperatePage(DictTypeConstants.PAGE_LOGIN);
                    operatelog.setOperateType(DictConstants.BUTTON_LOGIN);
                    operatelog.setOperator(user.getUsername());
                    operatelog.setOperateTime(date);
                    operatelog.setStatus(SysConstants.ACTION_SUCCESS);
                    operatelog.setOperateDesc(IPUtil.getIp(request));
                    operatelogDao.save(operatelog);
                    return RespJsonFactory.buildSuccess();
                }
            }
        } catch (Exception e) {
            OperatelogEntity operatelog = new OperatelogEntity();
            System.out.println(PrimaryKeyUtils.uniqueId());
            operatelog.setId(PrimaryKeyUtils.uniqueId());
            operatelog.setOperatePage(DictTypeConstants.PAGE_LOGIN);
            operatelog.setOperateType(DictConstants.BUTTON_LOGIN);
            operatelog.setOperator(user.getUsername());
            operatelog.setOperateTime(date);
            operatelog.setStatus(SysConstants.ACTION_FAIL);
            operatelog.setOperateDesc(IPUtil.getIp(request));
            operatelogDao.save(operatelog);
            logger.error("账号密码验证异常", e);
            throw new APIException("账号密码验证异常:" + e.getMessage());
        }
    }
}
