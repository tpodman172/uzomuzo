package com.tpodman172.uzomuzo.server.context.task;

import com.tpodman172.uzomuzo.infra.schema.rds.Tables;
import com.tpodman172.uzomuzo.infra.schema.rds.tables.records.TasksRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TaskRepository implements ITaskRepository {

    @Autowired
    private DSLContext jooq;

    @Override
    public List<TaskEntity> find() {
        // そういえばコネクションのクローズはだれがやってくれるんだっけ…AOPか…
        List<TaskEntity> taskList = jooq.select().from(Tables.TASKS)
                .fetch()
                .stream()
                .map(record -> new TaskEntity(
                        record.get(Tables.TASKS.ID).longValue(),
                        record.get(Tables.TASKS.TITLE)))
                .collect(Collectors.toList());

        return taskList;
    }

    @Override
    public Long create(TaskEntity taskEntity) {
        final TasksRecord tasksRecord =
                jooq.insertInto(Tables.TASKS, Tables.TASKS.TITLE)
                        .values(taskEntity.getTitle())
                        .returning(Tables.TASKS.ID)
                        .fetchOne();
        return tasksRecord.getId();
    }
}
