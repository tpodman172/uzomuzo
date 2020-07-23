package com.tpodman172.tsk2.server.context.user;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findByEmail(String email);

    UserEntity findById(Long id);

    Long insert(UserEntity userEntity);

    void update(UserEntity userEntity);
}