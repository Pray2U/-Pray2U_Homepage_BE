package com.pray2you.p2uhomepage.global.exception.ErrorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}
