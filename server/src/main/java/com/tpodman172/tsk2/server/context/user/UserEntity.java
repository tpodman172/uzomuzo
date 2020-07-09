package com.tpodman172.tsk2.server.context.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity{
   private Long userId;
   private String password;
   private String email;
}
