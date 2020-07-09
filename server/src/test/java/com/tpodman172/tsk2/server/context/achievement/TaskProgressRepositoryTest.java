package com.tpodman172.tsk2.server.context.achievement;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.tpodman172.tsk2.infra.schema.rds.tables.Achievement.ACHIEVEMENT;

@SpringBootTest
@Transactional
class TaskProgressRepositoryTest {

    @Autowired
    private DSLContext jooq;

    @Test
    public void update() {
        jooq.insertInto(ACHIEVEMENT, ACHIEVEMENT.TASK_ID, ACHIEVEMENT.TARGET_DATE, ACHIEVEMENT.COMPLETED)
                .values(4L, LocalDate.now(), false).execute();

        jooq.selectFrom(ACHIEVEMENT).fetch();
    }
}