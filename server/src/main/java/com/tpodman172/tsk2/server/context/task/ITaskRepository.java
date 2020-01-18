package com.tpodman172.tsk2.server.context.task;

import java.util.List;

public interface ITaskRepository {
    List<TaskEntity> find();

    Long create(TaskEntity taskEntity);
}
