package com.bsg.api.exception;

/**
 * Created by zhang on 2017/3/28.
 * @description 代表http接口运行出错
 */
public class HttpException extends Exception {

    private static final long serialVersionUID = 1L;

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
