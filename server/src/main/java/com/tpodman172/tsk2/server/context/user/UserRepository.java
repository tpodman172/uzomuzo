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
    public Optional<UserEntity> findByEmail(String email) {
        System.out.println(email);
        return jooq.selectFrom(USER)
                .where(USER.USER_NAME.eq(email))
                .fetchOptionalInto(USER)
                .map(record ->
                        new UserEntity(record.getUserId(), record.getPassword(), record.getUserName())
                );
    }

    @Override
    public void insert(UserEntity userEntity) {
        jooq.insertInto(USER, USER.USER_NAME, USER.PASSWORD)
                .values(userEntity.getEmail(), userEntity.getPassword())
                .execute();
    }
}
