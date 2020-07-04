package com.tpodman172.tsk2.server.context.task;

import com.tpodman172.tsk2.infra.schema.rds.tables.records.TaskRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.TASK;


@Component
public class TaskRepository implements ITaskRepository {

    @Autowired
    private DSLContext jooq;

    @Override
    public List<TaskEntity> findByIds(Collection<Long> taskIds) {
        return jooq.selectFrom(TASK).where(TASK.TASK_ID.in(taskIds))
                .fetchStream()
                .map(taskRecord -> new TaskEntity(taskRecord.getTaskId(), taskRecord.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskEntity> find() {
        // そういえばコネクションのクローズはだれがやってくれるんだっけ…AOPか…
        System.out.println(jooq.configuration());
        List<TaskEntity> taskList = jooq.select().from(TASK)
                .fetch()
                .stream()
                .map(record -> new TaskEntity(
                        record.get(TASK.TASK_ID).longValue(),
                        record.get(TASK.TITLE)))
                .collect(Collectors.toList());

        return taskList;
    }

    @Override
    public Long create(TaskEntity taskEntity) {
        final TaskRecord tasksRecord =
                jooq.insertInto(TASK, TASK.USER_ID ,TASK.TITLE)
                        .values(1L, taskEntity.getTitle()) // todo implement
                        .returning(TASK.TASK_ID)
                        .fetchOne();
        return tasksRecord.getTaskId();
    }
}
