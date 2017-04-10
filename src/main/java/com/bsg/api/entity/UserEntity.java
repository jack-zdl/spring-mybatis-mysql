package com.bsg.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhang on 2017/3/28.
 */
public class UserEntity extends BaseEntity implements Serializable{
    /**
     * @description
     */
    private static final long serialVersionUID = 1L;
    /**
     * @description 登录名
     */
    private String username;
    /**
     * @description 用户名
     */
    private String name;
    /**
     * @description 登录密码
     */
    private String password;
    /**
     * @description 是否有效
     */
    private Boolean validate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public UserEntity() {
    }

    public UserEntity(Date createDateTime, String createUserLoginName, String username, String name, String password, Boolean validate) {
        super(createDateTime, createUserLoginName);
        this.username = username;
        this.name = name;
        this.password = password;
        this.validate = validate;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", validate=" + validate +
                '}';
    }
}
