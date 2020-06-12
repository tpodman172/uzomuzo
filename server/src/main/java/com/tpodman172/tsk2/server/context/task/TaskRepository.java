package com.tpodman172.tsk2.server.context.task;

import com.tpodman172.tsk2.infra.schema.rds.Tables;
import com.tpodman172.tsk2.infra.schema.rds.tables.records.TaskRecord;
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
        System.out.println(jooq.configuration());
        List<TaskEntity> taskList = jooq.select().from(Tables.TASK)
                .fetch()
                .stream()
                .map(record -> new TaskEntity(
                        record.get(Tables.TASK.TASK_ID).longValue(),
                        record.get(Tables.TASK.TITLE)))
                .collect(Collectors.toList());

        return taskList;
    }

    @Override
    public Long create(TaskEntity taskEntity) {
        final TaskRecord tasksRecord =
                jooq.insertInto(Tables.TASK, Tables.TASK.TITLE)
                        .values(taskEntity.getTitle())
                        .returning(Tables.TASK.TASK_ID)
                        .fetchOne();
        return tasksRecord.getTaskId();
    }
}
