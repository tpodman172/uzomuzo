package com.tpodman172.tsk2.server.context.task;

import com.tpodman172.tsk2.infra.schema.rds.tables.records.TaskRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.tpodman172.tsk2.infra.schema.rds.Tables.TASK;


@Component
@RequiredArgsConstructor
public class TaskRepository implements ITaskRepository {

    private final DSLContext jooq;

    @Override
    public List<TaskEntity> findByUserId(Long userId) {
        System.out.println(jooq.configuration());
        List<TaskEntity> taskList =
                jooq.select()
                        .from(TASK)
                        .where(TASK.USER_ID.eq(userId))
                        .fetch()
                        .stream()
                        .map(record -> new TaskEntity(
                                record.get(TASK.TASK_ID),
                                record.get(TASK.USER_ID),
                                record.get(TASK.TITLE)))
                        .collect(Collectors.toList());

        return taskList;
    }

    @Override
    public Long create(TaskEntity taskEntity) {
        final TaskRecord tasksRecord =
                jooq.insertInto(TASK, TASK.USER_ID, TASK.TITLE)
                        .values(taskEntity.getUserId(), taskEntity.getTitle())
                        .returning(TASK.TASK_ID)
                        .fetchOne();
        return tasksRecord.getTaskId();
    }
}
