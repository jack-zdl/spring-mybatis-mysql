package com.bsg.api.dao;

import com.bsg.api.entity.BookEntity;
import com.bsg.api.util.Page;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by zhang on 2017/3/28.
 */
public interface BookDao extends CrudDao<BookEntity> {
    List<BookEntity> findPage(Page<BookEntity> page);
}
