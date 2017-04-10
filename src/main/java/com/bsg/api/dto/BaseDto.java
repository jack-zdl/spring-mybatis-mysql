package com.bsg.api.dto;

import java.util.Date;

/**
 * Created by zhang on 2017/3/31.
 */
public class BaseDto {
    /**
     * @description id
     */
    private String id;
    /**
     * @description 创建者
     */
    private String createUserName;
    /**
     * @description 创建时间
     */
    private Date createDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
