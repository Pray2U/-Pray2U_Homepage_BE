package com.pray2you.p2uhomepage.global.exception.errorcode;

import org.springframework.http.HttpStatus;

import java.io.Serializable;


public interface ErrorCode extends Serializable {
    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}
