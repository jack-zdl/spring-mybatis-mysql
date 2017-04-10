package com.bsg.api.entity;

import java.util.Date;

/**
 * Created by zhang on 2017/3/29.
 */
public class BaseEntity {
    /**
     * 创建时间
     */
    private Date createDateTime;

    /**
     * 创建者
     */
    private String createUserLoginName;

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getCreateUserLoginName() {
        return createUserLoginName;
    }

    public void setCreateUserLoginName(String createUserLoginName) {
        this.createUserLoginName = createUserLoginName;
    }

    public BaseEntity(Date createDateTime, String createUserLoginName) {
        this.createDateTime = createDateTime;
        this.createUserLoginName = createUserLoginName;
    }

    public BaseEntity() {
    }
}
