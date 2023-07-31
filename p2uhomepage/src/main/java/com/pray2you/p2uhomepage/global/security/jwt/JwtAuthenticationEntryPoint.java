package com.pray2you.p2uhomepage.global.security.jwt;

import com.pray2you.p2uhomepage.global.exception.ErrorCode.ErrorCode;
import com.pray2you.p2uhomepage.global.exception.ErrorCode.UserErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String)request.getAttribute("exception");
        ErrorCode errorCode;

        log.debug(" log: exception: {}", exception);

        /*
        토큰 없는 경우
        */
        if (authException == null) {
            errorCode = UserErrorCode.NO_TOKEN_EXCEPTION;
            setResponse(response, errorCode);
            return;
        }

        /*
        토큰이 이상한 경우
        */
        if (exception.equals(UserErrorCode.INVALID_TOKEN.name())) {
            errorCode = UserErrorCode.INVALID_TOKEN;
            setResponse(response, errorCode);
            return;
        }

        /*
        토큰이 만료된 경우
        */
        if (exception.equals(UserErrorCode.EXPIRED_TOKEN_EXCEPTION.name())) {
            errorCode = UserErrorCode.EXPIRED_TOKEN_EXCEPTION;
            setResponse(response, errorCode);
        }
    }


    /*
     한글 출력을 위해 getWriter 사용
    */
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        StringBuilder responseJson = new StringBuilder();
        responseJson.append("{")
                .append("\"status\": \"").append(errorCode.getHttpStatus().value()).append("\", ")
                .append("\"error\": \"").append(errorCode.name()).append("\", ")
                .append("\"msg\": \"").append(errorCode.getMessage()).append("\"")
                .append("}");

        response.getWriter().println(responseJson);
    }
}
