package com.bsg.api.service;

import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhang on 2017/4/17.
 */
@Service()
public class UserService extends BaseService{

    @Resource
    private UserDao userDao;
    /**
     * @description 单一原则 查询列表
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @description 获取单个对象
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        try {

        }catch (Exception e){
            throw new APIException();
        }
        return null;
    }

    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @description 更新用户信息,更新用户金额，金额不足回滚
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    @Override
    @Transactional(rollbackFor =  APIException.class)
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try{
            HttpSession session = request.getSession();
            UserEntity userEntity = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
            int bookPrice = Integer.parseInt((String) param.get("money"));
            int money = Integer.parseInt(userEntity.getMoney());
            String afterMoney = String.valueOf(money - bookPrice);
            userEntity.setMoney(String.valueOf(money - bookPrice));
            userDao.update(userEntity);
            if(money < bookPrice){
                respJson = RespJsonFactory.buildWarning("更新用户金额错误");
                throw new APIException("更新用户金额错误");
            }else{
                respJson = RespJsonFactory.buildSuccess("更新用户金额成功");
            }
        }catch(Exception e){
            System.out.println("更新用户信息错误"+e.getMessage());
            e.printStackTrace();
        }
        return respJson;
    }

    /**
     * @description 删除用户
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    @Override
    public RespJson remove(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }
}
