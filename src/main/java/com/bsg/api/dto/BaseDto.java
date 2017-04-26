package com.bsg.api.dto;

import java.util.Date;

/**
 * Created by zhang on 2017/3/31.
 */
public class BaseDto {
    /**
     * @description 创建者
     */
    private String createUserName;
    /**
     * @description 创建时间
     */
    private Date createDateTime;

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public BaseDto(String createUserName, Date createDateTime) {
        this.createUserName = createUserName;
        this.createDateTime = createDateTime;
    }

    public BaseDto() {
    }
}
