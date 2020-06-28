package com.tpodman172.tsk2.server.context.achievement;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.ACHIEVEMENT;

@Repository
@RequiredArgsConstructor
public class AchievementRepository implements IAchievementRepository {

    private final DSLContext jooq;

    @Override
    public List<AchievementEntity> findByTargetDate(LocalDate targetDate) {
        return jooq.selectFrom(ACHIEVEMENT)
                .where(ACHIEVEMENT.TARGET_DATE.eq(targetDate))
                .fetchStream()
                .map(taskProgressRecord ->
                        new AchievementEntity(taskProgressRecord.getTaskId(),
                                taskProgressRecord.getTargetDate(),
                                taskProgressRecord.getCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(AchievementEntity entity) {
        jooq.insertInto(
                ACHIEVEMENT,
                ACHIEVEMENT.TASK_ID,
                ACHIEVEMENT.TARGET_DATE,
                ACHIEVEMENT.COMPLETED)
                .values(entity.getTaskId(), entity.getTargetDate(), entity.isCompleted())
                .onDuplicateKeyUpdate()
                .set(ACHIEVEMENT.COMPLETED, entity.isCompleted())
                .execute();
    }
}
