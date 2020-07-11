package com.tpodman172.tsk2.server.config;

import com.tpodman172.tsk2.server.base.authentication.SimpleLoginUser;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Slf4j
public class UserSessionService {

    public Long getUserId() {
        val principal = (SimpleLoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserId();
    }
}