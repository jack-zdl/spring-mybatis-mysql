package com.bsg.api.service;

import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.BookDao;
import com.bsg.api.dao.UserDao;
import com.bsg.api.entity.BookEntity;
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
import java.util.HashMap;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by zhang on 2017/3/28.
 */
@Service("bookService")
public class BookService extends BaseService{

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
        try{
            System.out.println((String) param.get("id"));
            BookEntity bookEntity = (BookEntity)bookDao.get((String) param.get("id"));
            respJson = RespJsonFactory.buildSuccess(bookEntity);
        }catch(Exception e){
            throw  new APIException("获取单个书籍异常"+e.getMessage());
        }
        return respJson;
    }

    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }
    /**
     * @description 1 单一原则 ：更新书籍的数量,数量不足rollback
     * @param request user
     * @param param bookId(唯一) boookNumber
     * @return
     * @throws APIException
     */
    @Transactional(rollbackFor = APIException.class)
    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        try{
            String bookId = (String) param.get("id");
            BookEntity bookEntity = bookDao.get(bookId);
            bookEntity.setNumber( String.valueOf(Integer.parseInt(bookEntity.getNumber())- Integer.parseInt((String) param.get("number"))));
            bookDao.update(bookEntity);
            int number = Integer.parseInt(bookEntity.getNumber());
            if ( Integer.parseInt((String) param.get("number")) >=  number ) {
               respJson = RespJsonFactory.buildWarning("书的库存不足,事物回滚");
            }else {
                respJson = RespJsonFactory.buildSuccess("更新书籍成功");
            }
        }catch(Exception e){
            throw new APIException("书的库存不足"+e.getMessage());
        }
        return respJson;
    }

    @Override
    public RespJson remove(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @description 1更新书籍数量
     * @param request
     * @param param bookId(唯一) boookNumber userId(系统自己判断)
     * @return
     */
//    @Transactional(rollbackFor = APIException.class)
//    public RespJson updateBook(HttpServletRequest request, Map<String, Object> param)throws  APIException{
//        RespJson respJson = null;
//        try{
//            String bookId = (String) param.get("id");
//            HttpSession session = request.getSession();
//            UserEntity userEntity = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
//            bookDao.update(param);
//            BookEntity bookEntity = bookDao.get(bookId);
//            int number = Integer.parseInt(bookEntity.getNumber());
//            if (0 >=  number ) {
//               respJson = RespJsonFactory.buildWarning("书的库存不足,事物回滚");
//            }else {
//                respJson = RespJsonFactory.buildSuccess("更新书籍成功");
//            }
//        }catch (Exception e){
//            respJson = RespJsonFactory.buildWarning("书的库存不足");
//            logger.error("书籍购买失败"+e);
//          throw  new APIException("书籍购买失败"+e.getMessage());
//
//        }
//       return  respJson;
//    }

    /**
     * @description 1修改书籍数量，数量不足rollback, 2 修改用户金额不足rollback
     * @param request
     * @param param
     * @return
     * @throws APIException
     */
    @Transactional(rollbackFor = APIException.class)
    public RespJson updateBook(HttpServletRequest request, Map<String, Object> param)throws  APIException{
        RespJson respJson = null;
        Map<String,Object> priceMap = new HashMap<String,Object>();
        try{
            respJson= update(request,param);
            String updateBookText = respJson.getMsg();
           // respJson = null;
            respJson = get(request,param);
            BookEntity bookEntity = (BookEntity) respJson.getData();
            String price =  bookEntity.getPrice();
            String number = (String) param.get("number");
            priceMap.put("money",String.valueOf(Integer.parseInt(number)*Integer.parseInt(price)));
         //   respJson = userService.update(request,priceMap);
        }catch (Exception e){
            logger.error("书籍购买失败"+e);
            throw  new APIException("书籍购买失败"+e.getMessage());
        }
       return  respJson;
    }

}
