package com.tpodman172.tsk2.server.context.taskChallengeResult;

import java.time.LocalDate;
import java.util.List;

public interface ITaskChallengeResultRepository {
    void update(TaskChallengeResultEntity entity);

    List<TaskChallengeResultEntity> findByTargetDate(LocalDate localDate);
}
