package com.bsg.api.exception;

/**
 * Created by zhang on 2017/3/28.
 *
 * @description 代表http接口运行出错
 */
public class HttpClientException extends Exception {

    private static final long serialVersionUID = 1L;

    public HttpClientException() {
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

    public HttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
