package com.tpodman172.tsk2.server.config;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;

@Component
@RequestScope
@RequiredArgsConstructor
@Slf4j
public class UserSessionService {
    @NonNull
    private final HttpServletRequest request;
    private Long userId;

    public Long getUserId() {
        final val headerNames = request.getHeaderNames();
        System.out.println("-----------print header----------");
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.println(name);
            System.out.println(request.getHeader(name));
        }

        // todo extract user id from header and return the id
        return 1L;
    }
}