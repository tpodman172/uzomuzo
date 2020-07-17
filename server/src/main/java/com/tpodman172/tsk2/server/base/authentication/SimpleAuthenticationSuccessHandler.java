package com.tpodman172.tsk2.server.base.authentication;

import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final KeyConfig keyConfig;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) {
        if (response.isCommitted()) {
            log.info("Response has already been committed.");
            return;
        }

        try {
            SimpleLoginUser user = (SimpleLoginUser) auth.getPrincipal();
            String token = JwtTokenUtil.createJwtToken(keyConfig, user.getUserId(), user.getUsername());
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
            response.setHeader(HttpHeaders.AUTHORIZATION, token);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
        response.setStatus(HttpStatus.OK.value());
    }


}

