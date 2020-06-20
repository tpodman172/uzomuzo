package com.tpodman172.tsk2.server.context.task;

import java.util.Collection;
import java.util.List;

public interface ITaskRepository {
    List<TaskEntity> find();

    List<TaskEntity> findByIds(Collection<Long> taskIds);

    Long create(TaskEntity taskEntity);
}
