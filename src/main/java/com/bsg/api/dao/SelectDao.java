package com.bsg.api.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2017/5/4.
 *
 * @description 专门对数据库进行查询的
 */

public interface SelectDao {
    List list();

    List get(Map<String, Object> param);
}
