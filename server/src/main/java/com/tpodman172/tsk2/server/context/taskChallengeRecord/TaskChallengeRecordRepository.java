package com.tpodman172.tsk2.server.context.taskChallengeRecord;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.TASK_PROGRESS;

@Repository
@RequiredArgsConstructor
public class TaskChallengeRecordRepository implements ITaskChallengeRecordRepository {

    private final DSLContext jooq;

    @Override
    public List<TaskChallengeRecordEntity> findByTargetDate(LocalDate targetDate) {
        return jooq.selectFrom(TASK_PROGRESS)
                .where(TASK_PROGRESS.TARGET_DATE.eq(targetDate))
                .fetchStream()
                .map(taskProgressRecord ->
                        new TaskChallengeRecordEntity(taskProgressRecord.getTaskId(),
                                taskProgressRecord.getTargetDate(),
                                taskProgressRecord.getCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(TaskChallengeRecordEntity entity) {
        jooq.insertInto(
                TASK_PROGRESS,
                TASK_PROGRESS.TASK_ID,
                TASK_PROGRESS.TARGET_DATE,
                TASK_PROGRESS.COMPLETED)
                .values(entity.getTaskId(), entity.getTargetDate(), entity.isCompleted())
                .onDuplicateKeyUpdate()
                .set(TASK_PROGRESS.COMPLETED, entity.isCompleted())
                .execute();
    }
}
