package com.bsg.api.entity;

import java.util.Date;

/**
 * Created by zhang on 2017/3/31.
 */
public class Operatelog {
    /**
     * @description id
     */
    private int id;
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
    private Date  operateTime;
    /**
     * @description 操作解释
     */
    private String operateDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public Operatelog(int id, String operatePage, String operateType, String operator, Date operateTime, String operateDesc) {
        this.id = id;
        this.operatePage = operatePage;
        this.operateType = operateType;
        this.operator = operator;
        this.operateTime = operateTime;
        this.operateDesc = operateDesc;
    }

    public Operatelog() {
    }


}
