package com.bsg.api.service;

import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @description 登录服务层
 * @// TODO: 2017/3/29
 * Created by zhang on 2017/3/29.
 */

@Service("loginService")
public class LoginServiceImpl extends BaseService{

    private static Logger logger = Logger.getLogger(LoginServiceImpl.class);

    @Resource
    private UserDao userDao;

    /**
     * @description 登录方法 
     * @// TODO: 2017/3/29  1添加日志操作
     * @param request
     * @param param
     * @return
     */
    @Transactional(rollbackFor = APIException.class)
    public RespJson login(HttpServletRequest request, Map<String,Object> param) throws APIException {
        RespJson respJson = null;
        try {
           // String password = (String) param.get("password");
            UserEntity user = userDao.getUser(param);
            if(user == null){
                return RespJsonFactory.buildWarning("用户名密码错误");
            }else {
                if(!user.getValidate()){
                    return RespJsonFactory.buildWarning("用户无权限登录");
                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute(SysConstants.SESSION_USER, user);
                    return RespJsonFactory.buildSuccess();
                }
            }

        }catch (Exception e){
            logger.error("账号密码验证异常", e);
            throw new APIException("账号密码验证异常:" + e.getMessage());
        }
    }
}
