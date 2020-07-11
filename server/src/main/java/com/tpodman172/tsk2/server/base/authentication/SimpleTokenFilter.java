package com.tpodman172.tsk2.server.base.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
public class SimpleTokenFilter extends GenericFilterBean {
    private static final String testTempPublicKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqwB8LYu5lAnQcBmWuhhy" +
                    "iJS0VH3lZxlAeG3L/EtrzALrJgVSSi1Mucs0r/icvofXF26SrW32K5QcqpHSiAw/" +
                    "3mWbiQZOu+PdR3RQ17CFjioudTfFvOmr9oy2Zp6SWJkfwhMqxswWYvcAHEEyJLtK" +
                    "U72cINtNHYCNcEVQ4xANXQ9vDqqt+0fjw9PlO4oJ45lijZilX2h9U3TvzefzRr//" +
                    "GjeNMFZohs1f+LYV2vM936VF8drtW8OEDII4wiARRM6PSoElJ8JPMJV53tAxskO4" +
                    "wUA0oxPU1dFsMNyb7WbgONjtqCUKmGKUngUfvuHawjtxxlwM3mQ51NKqdKAJLjeu" +
                    "mwIDAQAB";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilterが呼ばれました");
        final val token = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            KeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(testTempPublicKey));
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, null);
            JWTVerifier verifier = JWT.require(algorithm).build();
            val jwt = verifier.verify(token);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            jwt.getSubject(),
                            null,
                            AuthorityUtils.NO_AUTHORITIES)
            );
        } catch (JWTVerificationException e) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        chain.doFilter(request, response);
    }
}