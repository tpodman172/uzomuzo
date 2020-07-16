package com.tpodman172.tsk2.server.base.authentication;

import com.tpodman172.tsk2.server.context.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SimpleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        System.out.println("loadUserByUserNameが呼ばれました");
        return userRepository.findByEmail(email)
                .map(user -> new SimpleLoginUser(user.getUserId(), user.getEmail(), user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}

