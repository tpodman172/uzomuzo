package com.tpodman172.tsk2.server.context.user;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.USER;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final DSLContext jooq;

    @Override
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        return jooq.selectFrom(USER)
                .where(USER.EMAIL.eq(email))
                .and(USER.PASSWORD.eq(password))
                .fetchOptionalInto(USER)
                .map(record ->
                        new UserEntity(record.getUserId(), record.getPassword(), record.getEmail())
                );
    }
}
