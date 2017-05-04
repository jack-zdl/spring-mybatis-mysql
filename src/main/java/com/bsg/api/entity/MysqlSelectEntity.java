package com.bsg.api.entity;

/**
 * Created by zhang on 2017/5/4.
 */
public class MysqlSelectEntity {
    /**
     * @description 查询值名称 'Com_insert','Com_update','Com_delete','Com_select','open_tables'
     */
    private String variableName;
    /**
     * @description 查询值数据
     */
    private String value;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MysqlSelectEntity(String variableName, String value) {
        this.variableName = variableName;
        this.value = value;
    }

    public MysqlSelectEntity() {
    }

    @Override
    public String toString() {
        return "MysqlSelectEntity{" +
                "variableName='" + variableName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
