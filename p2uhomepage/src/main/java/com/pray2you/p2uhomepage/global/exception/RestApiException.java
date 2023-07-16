package com.pray2you.p2uhomepage.global.exception;

import com.pray2you.p2uhomepage.global.exception.ErrorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;

}
