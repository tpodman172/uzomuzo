package com.tpodman172.tsk2.server.context.taskProgress;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.tpodman172.tsk2.infra.schema.rds.tables.TaskDailyProgress.TASK_DAILY_PROGRESS;

@SpringBootTest
@Transactional
class TaskProgressRepositoryTest {

    @Autowired
    private DSLContext jooq;

    @Test
    public void update() {
        jooq.insertInto(TASK_DAILY_PROGRESS, TASK_DAILY_PROGRESS.TASK_ID, TASK_DAILY_PROGRESS.TARGET_DATE, TASK_DAILY_PROGRESS.COMPLETED)
                .values(4L, LocalDate.now(), false).execute();

        jooq.selectFrom(TASK_DAILY_PROGRESS).fetch();
    }
}