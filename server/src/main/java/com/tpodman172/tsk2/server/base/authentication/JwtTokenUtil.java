package com.tpodman172.tsk2.server.base.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenUtil {

    public static String createJwtToken(KeyConfig keyConfig, Long userId, String userName) {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 30L * 24L * 60L * 60L * 1000L);

        Algorithm algorithm = Algorithm.RSA256(null, keyConfig.getPrivateKey());
        String token = JWT.create()
                .withSubject(String.valueOf(userId))
                .withClaim("tsk2_user_name", userName)
                .withIssuer("tsk2.me")
                .withExpiresAt(expireTime)
                .withIssuedAt(new Date())
                .sign(algorithm);

        return token;
    }
}
