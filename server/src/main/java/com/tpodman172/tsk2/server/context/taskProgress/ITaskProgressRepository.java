package com.tpodman172.tsk2.server.context.taskProgress;

import java.time.LocalDate;
import java.util.List;

public interface ITaskProgressRepository {
    void update(TaskProgressEntity entity);

    List<TaskProgressEntity> findByTargetDate(LocalDate localDate);
}
