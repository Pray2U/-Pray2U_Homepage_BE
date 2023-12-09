package com.pray2you.p2uhomepage.global.exception;

import com.pray2you.p2uhomepage.global.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private static final long serialVersionUID = 8747231388755467240L;
    private final ErrorCode errorCode;

}
