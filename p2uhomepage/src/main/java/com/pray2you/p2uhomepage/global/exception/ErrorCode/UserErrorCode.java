package com.pray2you.p2uhomepage.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    NOT_FOUND_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "저장된 리프레시 토큰이 없습니다."),
    NOT_MATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 일치하지 않습니다."),
    NO_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "해당 토큰이 존재하지 않습니다."),
    EXPIRED_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "해당 토큰이 만료되었습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "사용할 수 없는 토큰입니다."),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "해당 api에 접근 권한이 없습니다."),
    NOT_MATCHED_REDIRECT_URI(HttpStatus.BAD_REQUEST, "Redirect URI가 맞지 않습니다."),
    DUPLICATE_APPROVAL_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 가입승인입니다."),
    NOT_EXIST_APPROVAL_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 가입승인입니다."),
    NOT_EXIST_USER_EXCEPTION(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),
    NOT_EXIST_EVENT_EXCEPTION(HttpStatus.NOT_FOUND, "해당 이벤트는 존재하지 않습니다."),
    NOT_EXIST_ITEM_EXCEPTION(HttpStatus.NOT_FOUND, "해당 아이템은 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}