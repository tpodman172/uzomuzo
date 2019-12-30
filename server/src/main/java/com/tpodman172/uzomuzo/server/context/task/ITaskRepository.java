package com.tpodman172.uzomuzo.server.context.task;

import java.util.List;

public interface ITaskRepository {
    List<TaskEntity> find();

    Long create(TaskEntity taskEntity);
}
