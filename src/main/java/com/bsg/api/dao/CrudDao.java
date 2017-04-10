package com.bsg.api.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 * @description 这是使用了泛型，具体根据传入的值来判断传入类型
 * @// TODO: 2017/3/28 做增删改查一些基本的查询
 */
public interface CrudDao<T> {

    /**
     *@description 查询所有的元素
     * @param param
     * @return
     */
    List<T> list(Map<String,Object> param);

    /**
     * @description 查询单个记录
     * @param id
     * @return
     */
    T get(String id);

    /**
     * @description 新增对象
     * @param entiry
     * @return
     */
    int save(T entiry);

    /**
     * @description 更新记录
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * @description 删除
     * @param id
     * @return
     */
    int remove(String id);
}
