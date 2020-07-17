package com.tpodman172.tsk2.server.api.appService;

import com.tpodman172.tsk2.server.context.user.IUserRepository;
import com.tpodman172.tsk2.server.context.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAppService {

   private final PasswordEncoder passwordEncoder;

   private final IUserRepository userRepository;

   public Long createUser(String userName, String password) {
      return userRepository
              .insert(UserEntity.createUser(userName, passwordEncoder.encode(password)));
   }
}
