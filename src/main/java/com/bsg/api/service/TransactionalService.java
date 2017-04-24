package com.bsg.api.service;

import com.bsg.api.constant.DictConstants;
import com.bsg.api.constant.DictTypeConstants;
import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.BookDao;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.BookEntity;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.IPUtil;
import com.bsg.api.util.RespJson;
import com.bsg.api.util.RespJsonFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 */
@Service("bookService")
public class BookService extends BaseService {

    private static Logger logger = Logger.getLogger(BookService.class);
    @Resource
    private BookDao bookDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserService userService;


    @Override
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            System.out.println((String) param.get("id"));
            BookEntity bookEntity = (BookEntity) bookDao.get((String) param.get("id"));
            respJson = RespJsonFactory.buildSuccess(bookEntity);
        } catch (Exception e) {
            throw new APIException("获取单个书籍异常" + e.getMessage());
        }
        return respJson;
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
     * @description user
     * @description bookId(唯一) boookNumber
     * @description 1 单一原则 ：更新书籍的数量,数量不足rollback
     */
    @Transactional(rollbackFor = APIException.class, propagation = Propagation.REQUIRED)
    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try {
            String bookId = (String) param.get("id");
            BookEntity bookEntity = bookDao.get(bookId);
            int number = Integer.parseInt(bookEntity.getNumber());
            if (Integer.parseInt((String) param.get("number")) > number) {
                //  logger.error("书的库存不足。");
                throw new APIException("书的库存不足");
            } else {
                bookEntity.setNumber(String.valueOf(Integer.parseInt(bookEntity.getNumber()) - Integer.parseInt((String) param.get("number"))));
                bookDao.update(bookEntity);
                respJson = RespJsonFactory.buildSuccess("更新书籍成功");
            }
        } catch (Exception e) {
            //   logger.error("书的库存不足。");
            e.printStackTrace();
            throw new APIException("更新书籍数量失败" + e.getMessage());
        }
        return respJson;
    }

    @Override
    public RespJson remove(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @param request
     * @param param
     *         bookId(唯一) boookNumber userId(系统自己判断)
     * @return
     * @description 1更新书籍数量 2更新用户信息 使用单个事物管理
     */
    @Transactional(rollbackFor = APIException.class)
    public RespJson updateBookByOneTx(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        try {
            String bookId = (String) param.get("id");
            BookEntity bookEntity = bookDao.get(bookId);
            bookEntity.setNumber(String.valueOf(Integer.parseInt(bookEntity.getNumber()) - Integer.parseInt((String) param.get("number"))));
            bookDao.update(bookEntity);
            int number = Integer.parseInt(bookEntity.getNumber());
            if (0 > number) {
                logger.error("书的库存不足。");
                throw new APIException("书的库存不足,事物回滚");
            } else {
                respJson = RespJsonFactory.buildSuccess("更新书籍成功");
            }

            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("username", userEntity.getUsername());
            userMap.put("password", userEntity.getPassword());
            UserEntity userEntity1 = userDao.getUser(userMap);
            String price = bookEntity.getPrice();
            String bookNumber = (String) param.get("number");
            int bookPrice = Integer.parseInt((String.valueOf(Integer.parseInt(bookNumber) * Integer.parseInt(price))));
            int money = Integer.parseInt(userEntity1.getMoney());
            userEntity.setMoney(String.valueOf(money - bookPrice));
            userDao.update(userEntity);
            if (money < bookPrice) {
                logger.error("书的库存不足。");
                logger.error("更新用户金额失败。");
                throw new APIException("更新用户金额失败。");
            } else {
                respJson = RespJsonFactory.buildSuccess("更新用户金额成功");
            }

        } catch (Exception e) {
            logger.error("书籍购买失败" + e);
            throw new APIException("书籍购买失败" + e.getMessage());

        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return RespJson
     * @throws APIException
     * @description 1修改书籍数量，数量不足rollback, 2 修改用户金额不足rollback
     * @// TODO: 2017/4/19 1事物处理
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor = APIException.class)
    public RespJson updateBookByMoreTx(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        Map<String, Object> priceMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        try {
            respJson = update(request, param);
            int resultUpdateBook = respJson.getResult();
            String msgUpdateBook = respJson.getMsg();
            respJson = get(request, param);
            BookEntity bookEntity = (BookEntity) respJson.getData();
            String price = bookEntity.getPrice();
            String number = (String) param.get("number");
            priceMap.put("money", String.valueOf(Integer.parseInt(number) * Integer.parseInt(price)));
            respJson = userService.update(request, priceMap);
            int resultUpdateUser = respJson.getResult();
            String logUpdateUser = respJson.getMsg();
            respJson.setMsg(msgUpdateBook + logUpdateUser);
            if ((resultUpdateBook != 1) && (resultUpdateUser != 1)) {
                saveOperateLog(DictTypeConstants.PAGE_TYPE, DictConstants.BUTTON_UPDATE, userEntity.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
                throw new APIException(msgUpdateBook + logUpdateUser);
            } else {
                saveOperateLog(DictTypeConstants.PAGE_TYPE, DictConstants.BUTTON_UPDATE, userEntity.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
            }
        } catch (APIException e) {
            logger.error("书籍购买失败" + e);
            throw new APIException("书籍购买失败" + e.getMessage());
        }
        return respJson;
    }
}
