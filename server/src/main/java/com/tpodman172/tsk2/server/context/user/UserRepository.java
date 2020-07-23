package com.tpodman172.tsk2.server.context.user;

import com.tpodman172.tsk2.server.context.exception.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.USER;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final DSLContext jooq;

    @Override
    public UserEntity findById(Long id) {
        val userRecord = jooq.selectFrom(USER)
                .where(USER.USER_ID.eq(id))
                .fetchOne();
        return new UserEntity(userRecord.getUserId(), userRecord.getPassword(), userRecord.getUserName());
    }

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
    public void update(UserEntity userEntity) {
        try {
            jooq.update(USER)
                    .set(USER.USER_NAME, userEntity.getEmail())
                    .set(USER.PASSWORD, userEntity.getPassword())
                    .where(USER.USER_ID.eq(userEntity.getUserId()))
                    .execute();
        } catch (DuplicateKeyException e) {
            throw new ResourceConflictException("user name is duplicated", e);
        }
    }

    @Override
    public Long insert(UserEntity userEntity) {
        try {
            return jooq.insertInto(USER, USER.USER_NAME, USER.PASSWORD)
                    .values(userEntity.getEmail(), userEntity.getPassword())
                    .returning(USER.USER_ID)
                    .fetchOne()
                    .getUserId();
        } catch (DuplicateKeyException e) {
            throw new ResourceConflictException("user name is duplicated", e);
        }
    }


}
