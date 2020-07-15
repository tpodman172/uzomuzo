package com.tpodman172.tsk2.server.base.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

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
            Date expireTime = new Date();
            expireTime.setTime(expireTime.getTime() + 30L * 24L * 60L * 60L * 1000L);

            Algorithm algorithm = Algorithm.RSA256(null, keyConfig.getPrivateKey());
            SimpleLoginUser user = (SimpleLoginUser) auth.getPrincipal();
            String token = JWT.create()
                    .withSubject(String.valueOf(user.getUserId()))
                    .withClaim("tsk2_user_name", user.getUsername())
                    .withIssuer("tsk2.me")
                    .withExpiresAt(expireTime)
                    .withIssuedAt(new Date())
                    .sign(algorithm);

            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
            response.setHeader(HttpHeaders.AUTHORIZATION, token);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
        response.setStatus(HttpStatus.OK.value());
    }
}

