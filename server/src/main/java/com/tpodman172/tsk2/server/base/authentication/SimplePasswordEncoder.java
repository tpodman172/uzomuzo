package com.tpodman172.tsk2.server.base.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SimplePasswordEncoder extends BCryptPasswordEncoder {
}
