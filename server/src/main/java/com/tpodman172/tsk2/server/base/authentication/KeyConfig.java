package com.tpodman172.tsk2.server.base.authentication;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class KeyConfig {
    @Value("${rsa-key.public:#{null}}")
    private String envPublicKey;
    @Value("${rsa-key.private:#{null}}")
    private String envPrivateKey;

    @Getter
    private RSAPublicKey publicKey;
    @Getter
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (envPrivateKey != null && envPublicKey != null) {
            System.out.println("env parameter is set");
            KeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(envPrivateKey));
            this.privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);
            KeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(envPublicKey));
            this.publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
        } else {
            System.out.println("env parameter is not set");
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(2048);
            KeyPair kp = keyGenerator.genKeyPair();
            this.publicKey = (RSAPublicKey) kp.getPublic();
            this.privateKey = (RSAPrivateKey) kp.getPrivate();
        }
    }
}
