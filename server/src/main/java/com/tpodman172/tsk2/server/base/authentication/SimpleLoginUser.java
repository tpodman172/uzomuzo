package com.tpodman172.tsk2.server.base.authentication;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
public class SimpleLoginUser extends User {

    private final Long userId;

    public SimpleLoginUser(Long userId, String email, String password) {
        super(email, password, AuthorityUtils.NO_AUTHORITIES);
        this.userId = userId;
    }
}