package com.bsg.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhang on 2017/5/2.
 *
 * @description 5xx状态码
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "internal server error")
public class ServerException extends RuntimeException {
}
