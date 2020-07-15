package com.tpodman172.tsk2.server.base.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class SimpleTokenFilter extends GenericFilterBean {

    private final KeyConfig keyConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilterが呼ばれました");
        final val token = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            System.out.println("tokenがありませんでした");
            chain.doFilter(request, response);
            return;
        }

        try {
            Algorithm algorithm = Algorithm.RSA256(keyConfig.getPublicKey(), null);
            JWTVerifier verifier = JWT.require(algorithm).build();
            val jwt = verifier.verify(token.replace("Bearer ", ""));
            System.out.println(jwt.getClaim("tsk2_user_name").asString());
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            new SimpleLoginUser(Long.valueOf(jwt.getSubject()), jwt.getClaim("tsk2_user_name").asString(), ""),
                            null,
                            AuthorityUtils.NO_AUTHORITIES)
            );
            System.out.println("検証に成功しました");
        } catch (JWTVerificationException e) {
            System.out.println("検証にしっぱいしました");
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }

        chain.doFilter(request, response);
    }
}