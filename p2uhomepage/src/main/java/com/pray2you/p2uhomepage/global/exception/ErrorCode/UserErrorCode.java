package com.pray2you.p2uhomepage.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    NOT_MATCHED_REDIRECT_URI(HttpStatus.BAD_REQUEST, "Redirect URI가 맞지 않습니다."),
    DUPLICATE_APPROVAL_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 가입승인입니다."),
    NOT_EXIST_APPROVAL_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 가입승인입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}