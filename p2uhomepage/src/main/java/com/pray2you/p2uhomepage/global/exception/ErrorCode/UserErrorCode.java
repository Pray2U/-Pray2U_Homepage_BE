package com.pray2you.p2uhomepage.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    NOT_MATCHED_REDIRECT_URI(HttpStatus.BAD_REQUEST, "Redirect URIs are not matched"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}