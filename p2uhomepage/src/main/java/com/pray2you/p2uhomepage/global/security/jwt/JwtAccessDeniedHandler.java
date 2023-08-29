package com.pray2you.p2uhomepage.global.security.jwt;

import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        StringBuilder responseJson = new StringBuilder();
        responseJson.append("{")
                .append("\"status\": \"").append(UserErrorCode.ACCESS_DENIED.getHttpStatus().value()).append("\", ")
                .append("\"error\": \"").append(UserErrorCode.ACCESS_DENIED.name()).append("\", ")
                .append("\"msg\": \"").append(UserErrorCode.ACCESS_DENIED.getMessage()).append("\"")
                .append("}");

        response.getWriter().println(responseJson);
    }
}



