package com.bsg.api.service;

import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang on 2017/4/17.
 */
@Service()
public class UserService extends BaseService {

    @Resource
    private UserDao userDao;

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 单一原则 查询列表
     */
    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 获取单个对象
     */
    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        try {

        } catch (Exception e) {
            throw new APIException();
        }
        return null;
    }

    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 更新用户信息, 更新用户金额，金额不足回滚
     * @problem
     */
    @Transactional(rollbackFor = APIException.class, propagation = Propagation.REQUIRED)
    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("username", userEntity.getUsername());
        userMap.put("password", userEntity.getPassword());
        try {
            UserEntity userEntity1 = userDao.getUser(userMap);
            int bookPrice = Integer.parseInt((String) param.get("money"));
            int money = Integer.parseInt(userEntity1.getMoney());
            if (money < bookPrice) {
                respJson = RespJsonFactory.buildFailure("更新用户金额失败。");
                throw new APIException("更新用户金额失败。");
            } else {
                userEntity.setMoney(String.valueOf(money - bookPrice));
                userDao.update(userEntity);
                respJson = RespJsonFactory.buildSuccess("更新用户金额成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("更新用户信息错误-" + e.getMessage());
        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 删除用户
     */
    @Override
    public RespJson remove(HttpServletRequest request, String id) throws APIException {
        return null;
    }
}
