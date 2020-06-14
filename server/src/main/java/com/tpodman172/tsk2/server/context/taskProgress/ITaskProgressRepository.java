package com.tpodman172.tsk2.server.context.taskProgress;

import java.time.LocalDate;

public interface ITaskProgressRepository {
    void update(Long taskId, LocalDate targetDate, Boolean isCompleted);
}
