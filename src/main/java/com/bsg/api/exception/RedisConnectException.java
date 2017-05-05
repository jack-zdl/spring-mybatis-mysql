package com.bsg.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhang on 2017/5/5.
 */
@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE, reason = "not connect redis db")
public class RedisConnectException extends RuntimeException {
}
