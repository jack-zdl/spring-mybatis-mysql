package com.bsg.api.util;

/**
 * Created by zhang on 2017/3/29.
 * @description 定义返回的数据类型
 *      result  1;结果类型 1成功 0 警告 -1 失败 -9 未登录
 *      code   1;当result等于0时，触发的各种警告信息
 *      msg  ：“”后端返还的消息
 *      data: object 返回的具体数据
 */
public class RespJson {

    public static final int SUCCESS = 1;

    public static final int WARNING = 0;

    public static final int FAIL  =  -1;

    public static final int NOLOGIN = -9;

    private  int result;
    private  Integer code;
    private  String msg;
    private  Object data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespJson{" +
                "result=" + result +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
