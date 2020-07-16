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
    public Optional<UserEntity> findByEmailAndPassword(String email) {
        System.out.println("findByEmailAndPassword");
        System.out.println(email);
        return jooq.selectFrom(USER)
                .where(USER.EMAIL.eq(email))
                .fetchOptionalInto(USER)
                .map(record ->
                        new UserEntity(record.getUserId(), record.getPassword(), record.getEmail())
                );
    }

    @Override
    public void insert(UserEntity userEntity) {
        jooq.insertInto(USER, USER.EMAIL, USER.PASSWORD)
                .values(userEntity.getEmail(), userEntity.getPassword())
                .execute();
    }
}
