package com.bsg.api.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 * @description 这是使用了泛型，具体根据传入的值来判断传入类型
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

    int insertByBatch(List<T> entitys);

    /**
     * @description 更新记录
     * @param entity
     * @return
     */
   int update(T entity);
    //int update(Map param);
    /**
     * @description 删除
     * @param id
     * @return
     */
    int remove(String id);
}
