package com.bsg.api.service;

import com.bsg.api.exception.APIException;
import com.bsg.api.util.RespJson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhang on 2017/3/28.
 */
@Service("bookService")
public class BookServiceImpl {

    @Transactional(rollbackFor = APIException.class)
    public RespJson updateBook()throws APIException{
        RespJson respJson = null;

        return  respJson;
    }
}
