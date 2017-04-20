package com.bsg.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhang on 2017/3/31.
 */
public class OperatelogEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * @description id
     */
    private String id;
    /**
     * @description 操作页面
     */
    private String operatePage;
    /**
     * @description 操作类型
     */
    private String operateType;
    /**
     * @description 操作者
     */
    private String operator;
    /**
     * @description 操作时间
     */
    private Date operateTime;
    private int status;
    /**
     * @description 操作解释
     */
    private String operateDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperatePage() {
        return operatePage;
    }

    public void setOperatePage(String operatePage) {
        this.operatePage = operatePage;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public OperatelogEntity(Date createDateTime, String createUserLoginName, String id, String operatePage, String operateType, String operator, Date operateTime, int status, String operateDesc) {
        super();
        this.id = id;
        this.operatePage = operatePage;
        this.operateType = operateType;
        this.operator = operator;
        this.operateTime = operateTime;
        this.status = status;
        this.operateDesc = operateDesc;
    }

    public OperatelogEntity() {
    }

    @Override
    public String toString() {
        return "OperatelogEntity{" +
                "id='" + id + '\'' +
                ", operatePage='" + operatePage + '\'' +
                ", operateType='" + operateType + '\'' +
                ", operator='" + operator + '\'' +
                ", operateTime=" + operateTime +
                ", status=" + status +
                ", operateDesc='" + operateDesc + '\'' +
                '}';
    }
}
