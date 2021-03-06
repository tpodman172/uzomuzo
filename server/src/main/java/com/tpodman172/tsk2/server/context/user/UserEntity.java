package com.tpodman172.tsk2.server.context.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity {
   private Long userId;
   private String password;
   private String email;

   public static UserEntity createUser(String email, String password) {
      return new UserEntity(null, password, email);
   }

   public void updatePassword(String password) {
      if (password != null) {
         this.password = password;
      }
   }

   public void updateUserName(String userName) {
      this.email = userName;
   }
}
