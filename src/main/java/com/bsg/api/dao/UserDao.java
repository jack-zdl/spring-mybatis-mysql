package com.bsg.api.dao;

import com.bsg.api.entity.UserEntity;

import java.util.Map;

/**
 * Created by zhang on 2017/3/28.
 * @description 接口不能添加注解，无法new一个接口
 */
/**
 * @description 可以根据自己独特的查询自行增加
 */

public interface UserDao extends CrudDao<UserEntity> {

    /**
     * @description 获取用户信息
     * @param param
     * @return
     */
    public UserEntity getUser(Map<String, Object> param);

}
