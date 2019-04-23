package com.bsg.api.service;

import com.bsg.api.constant.DictConstants;
import com.bsg.api.constant.DictTypeConstants;
import com.bsg.api.constant.SysConstants;
import com.bsg.api.dao.BookDao;
import com.bsg.api.dto.BookDto;
import com.bsg.api.entity.BookEntity;
import com.bsg.api.entity.UserEntity;
import com.bsg.api.exception.APIException;
import com.bsg.api.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2017/4/21.
 */
@Service()
public class CurdBookService extends BaseService {
    private static Logger logger = Logger.getLogger(CurdBookService.class);
    @Resource
    private BookDao bookDao;

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 书籍查询添加日志
     */
    @Override
    @Transactional(rollbackFor = APIException.class)
    public RespJson list(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        List<BookDto> bookDtoList = new ArrayList<>();
        try {
            List<BookEntity> bookEntityList = bookDao.list(param);
            for (BookEntity bookEntity : bookEntityList) {
                BookDto bookDto = new BookDto();
                bookDto.setId(bookEntity.getId());
                bookDto.setNumber(bookEntity.getNumber());
                bookDto.setPrice(bookEntity.getPrice());
                bookDto.setCreateUserName(bookEntity.getCreateUserLoginName());
                bookDto.setCreateDateTime(bookEntity.getCreateDateTime());
                bookDtoList.add(bookDto);
            }
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SELECT, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
            respJson = RespJsonFactory.buildSuccess(bookDtoList);
//            logger.info("书籍查询成功");
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SELECT, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("书籍查询异常");
            e.printStackTrace();
            throw new APIException("书籍查询异常");
        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 书籍查询添加日志
     */
    @Transactional(rollbackFor = APIException.class)
    public RespJson page(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        List<BookDto> bookDtoList = new ArrayList<>();
        try {
            Integer pageNum =  1;
            Integer pageSize = 4;
            Page page = PageHelper.startPage(pageNum, pageSize, true);
            List<BookEntity> bookEntityList = bookDao.list(param);
            for (BookEntity bookEntity : bookEntityList) {
                BookDto bookDto = new BookDto();
                bookDto.setId(bookEntity.getId());
                bookDto.setNumber(bookEntity.getNumber());
                bookDto.setPrice(bookEntity.getPrice());
                bookDto.setCreateUserName(bookEntity.getCreateUserLoginName());
                bookDto.setCreateDateTime(bookEntity.getCreateDateTime());
                bookDtoList.add(bookDto);
            }
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SELECT, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
            respJson = RespJsonFactory.buildSuccess(bookDtoList);
//            logger.info("书籍查询成功");
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SELECT, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("书籍查询异常");
            e.printStackTrace();
            throw new APIException("书籍查询异常");
        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 书籍查询添加日志
     */
    @Transactional(rollbackFor = APIException.class)
    public RespJson excutors(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        List<BookDto> bookDtoList = new ArrayList<>();
        try {
            List<BookEntity> bookEntityList = bookDao.list(param);
            for (BookEntity bookEntity : bookEntityList) {
                BookDto bookDto = new BookDto();
                bookDto.setId(bookEntity.getId());
                bookDto.setNumber(bookEntity.getNumber());
                bookDto.setPrice(bookEntity.getPrice());
                bookDto.setCreateUserName(bookEntity.getCreateUserLoginName());
                bookDto.setCreateDateTime(bookEntity.getCreateDateTime());
                bookDtoList.add(bookDto);
            }
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SELECT, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
            respJson = RespJsonFactory.buildSuccess(bookDtoList);
//            logger.info("书籍查询成功");
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SELECT, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("书籍查询异常");
            e.printStackTrace();
            throw new APIException("书籍查询异常");
        }
        return respJson;
    }

    @Override
    public RespJson get(HttpServletRequest request, Map<String, Object> param) throws APIException {
        return null;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 新增
     */
    @Transactional(rollbackFor = APIException.class)
    @Override
    public RespJson save(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        try {
            BookEntity bookEntity = new BookEntity();
            String bookId = PrimaryKeyUtils.uniqueId();
            bookEntity.setId(bookId);
            String price = (String) param.get("price");
            bookEntity.setPrice(price);
            String number = (String) param.get("number");
            bookEntity.setNumber(number);
            bookEntity.setCreateUserLoginName(user.getUsername());
            bookEntity.setCreateDateTime(DateUtil.getCurrentDateTime());
            bookDao.save(bookEntity);
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SAVE, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
            respJson = RespJsonFactory.buildSuccess("书籍新增成功。");
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_SAVE, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("书籍新增发生异常");
            e.printStackTrace();
            throw new APIException("书籍新增发生异常");
        }
        return respJson;
    }

    /**
     * @param request
     * @param param
     * @return
     * @throws APIException
     * @description 更新书籍 保存日志
     */
    @Transactional(rollbackFor = APIException.class)
    @Override
    public RespJson update(HttpServletRequest request, Map<String, Object> param) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        Map<String, Object> idMap = new HashMap<String, Object>();
        try {
            String bookId = (String) param.get("id");
            idMap.put("id", bookId);
            List<BookEntity> bookEntityList = bookDao.list(idMap);
            if (bookEntityList != null) {

                bookEntityList.get(0).setNumber((String) param.get("number"));
                bookEntityList.get(0).setPrice((String) param.get("price"));
                bookDao.update(bookEntityList.get(0));
                saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_UPDATE, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
                respJson = RespJsonFactory.buildSuccess("更新书籍成功");
            } else {
                saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_UPDATE, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
                throw new APIException("更新的书籍不存在");
            }
        } catch (Exception e) {
            logger.error("更新的书籍不存在");
            e.printStackTrace();
            throw new APIException("书籍更新异常");
        }
        return respJson;
    }

    /**
     * @param request
     * @param id
     * @return
     * @throws APIException
     * @description 删除书籍 保存日志
     */
    @Transactional(rollbackFor = APIException.class)
    @Override
    public RespJson remove(HttpServletRequest request, String id) throws APIException {
        RespJson respJson = null;
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(SysConstants.SESSION_USER);
        Map<String, Object> idMap = new HashMap<String, Object>();
        try {
            // idMap.put("id", id);
            bookDao.remove(id);
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_DELETE, user.getUsername(), SysConstants.ACTION_SUCCESS, IPUtil.getIp(request));
            respJson = RespJsonFactory.buildSuccess("书籍删除成功");
        } catch (Exception e) {
            saveOperateLog(DictTypeConstants.PAGE_SELECT, DictConstants.BUTTON_DELETE, user.getUsername(), SysConstants.ACTION_FAIL, IPUtil.getIp(request));
            logger.error("对应书籍不存在");
            throw new APIException("对应书籍不存在");
        }
        return respJson;
    }
}
