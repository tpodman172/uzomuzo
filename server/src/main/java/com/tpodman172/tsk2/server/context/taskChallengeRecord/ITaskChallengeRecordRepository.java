package com.tpodman172.tsk2.server.context.taskChallengeRecord;

import java.time.LocalDate;
import java.util.List;

public interface ITaskChallengeRecordRepository {
    void update(TaskChallengeRecordEntity entity);

    List<TaskChallengeRecordEntity> findByTargetDate(LocalDate localDate);
}
