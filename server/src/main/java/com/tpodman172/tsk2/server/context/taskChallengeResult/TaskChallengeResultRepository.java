package com.tpodman172.tsk2.server.context.taskChallengeResult;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.TASK_CHALLENGE_RESULT;

@Repository
@RequiredArgsConstructor
public class TaskChallengeResultRepository implements ITaskChallengeResultRepository {

    private final DSLContext jooq;

    @Override
    public List<TaskChallengeResultEntity> findByTargetDate(LocalDate targetDate) {
        return jooq.selectFrom(TASK_CHALLENGE_RESULT)
                .where(TASK_CHALLENGE_RESULT.TARGET_DATE.eq(targetDate))
                .fetchStream()
                .map(taskProgressRecord ->
                        new TaskChallengeResultEntity(taskProgressRecord.getTaskId(),
                                taskProgressRecord.getTargetDate(),
                                taskProgressRecord.getCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(TaskChallengeResultEntity entity) {
        jooq.insertInto(
                TASK_CHALLENGE_RESULT,
                TASK_CHALLENGE_RESULT.TASK_ID,
                TASK_CHALLENGE_RESULT.TARGET_DATE,
                TASK_CHALLENGE_RESULT.COMPLETED)
                .values(entity.getTaskId(), entity.getTargetDate(), entity.isCompleted())
                .onDuplicateKeyUpdate()
                .set(TASK_CHALLENGE_RESULT.COMPLETED, entity.isCompleted())
                .execute();
    }
}
