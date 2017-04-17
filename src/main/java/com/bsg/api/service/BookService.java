package com.bsg.api.service;

import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.BookDao;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 */
@Service("bookService")
public class BookService extends BaseService{

    @Resource
    private BookDao bookDao;
    @Resource
    private UserDao userDao;


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
    /**
     * @description 更新
     * @param request user
     * @param param bookName boookNumber 
     * @return
     * @throws APIException
     */
    @Override
    @Transactional(rollbackFor = APIException.class)
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try{
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);

            //  saveOperateLog();
            return  respJson;
        }catch (Exception e){
            throw  new APIException("书籍更新异常"+e.getMessage());
        }
    }

    @Override
    public RespJson remove(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }
}
