package com.tpodman172.tsk2.server.context.taskProgress;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.TASK_DAILY_PROGRESS;

@Component //todo -> @Repository
public class TaskProgressRepository implements ITaskProgressRepository {

    @Autowired //todo -> remove field annotation
    private DSLContext jooq;

    @Override
    public void update(TaskProgressEntity entity) {
        jooq.insertInto(
                TASK_DAILY_PROGRESS,
                TASK_DAILY_PROGRESS.TASK_ID,
                TASK_DAILY_PROGRESS.TARGET_DATE,
                TASK_DAILY_PROGRESS.COMPLETED)
                .values(entity.getTaskId(), entity.getTargetDate(), entity.isCompleted())
                .onDuplicateKeyUpdate()
                .set(TASK_DAILY_PROGRESS.COMPLETED, entity.isCompleted())
                .execute();
    }
}
