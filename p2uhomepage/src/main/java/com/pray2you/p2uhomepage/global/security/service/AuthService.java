package com.pray2you.p2uhomepage.global.security.service;

import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import com.pray2you.p2uhomepage.global.security.jwt.JwtTokenProvider;
import com.pray2you.p2uhomepage.global.security.refreshtoken.RefreshToken;
import com.pray2you.p2uhomepage.global.security.repository.RefreshTokenRepository;
import com.pray2you.p2uhomepage.global.security.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider tokenProvider;

    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue).orElseThrow(() -> new RestApiException(UserErrorCode.NO_TOKEN_EXCEPTION));

        if(!tokenProvider.validateToken(oldRefreshToken, request)) {
            throw new RestApiException(UserErrorCode.INVALID_TOKEN);
        }

        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        RefreshToken savedToken = refreshTokenRepository.findById(String.valueOf(user.getName()))
                .orElseThrow(()-> new RestApiException(UserErrorCode.NOT_FOUND_REFRESH_TOKEN));

        if (!savedToken.getToken().equals(oldRefreshToken)) {
            throw new RestApiException(UserErrorCode.NOT_MATCH_REFRESH_TOKEN);
        }

        String accessToken = tokenProvider.createAccessToken(authentication);
        tokenProvider.createRefreshToken(authentication, response);

        return accessToken;
    }

}
