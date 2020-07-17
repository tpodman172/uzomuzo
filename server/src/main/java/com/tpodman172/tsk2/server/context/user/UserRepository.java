package com.tpodman172.tsk2.server.context.user;

import com.tpodman172.tsk2.infra.schema.rds.tables.records.UserRecord;
import com.tpodman172.tsk2.server.context.exception.ResourceConflictException;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.springframework.dao.DuplicateKeyException;
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
