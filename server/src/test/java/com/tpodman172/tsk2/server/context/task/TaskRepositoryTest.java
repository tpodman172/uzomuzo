package com.tpodman172.tsk2.server.context.task;

import com.tpodman172.tsk2.infra.schema.rds.tables.records.TaskRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.tpodman172.tsk2.infra.schema.rds.tables.Task.TASK;

@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private ITaskRepository taskRepository;


    @Autowired
    private DSLContext jooq;

    @Test
    void create() throws Exception {
        final Long taskId = taskRepository.create(new TaskEntity(null, 1L, "testTitle"));

        final TaskRecord tasksRecord = jooq.selectFrom(TASK)
                .where(TASK.TASK_ID.eq(taskId))
                .fetchOne().into(TASK);

        assert (tasksRecord.getTitle()).equals("testTitle");
    }
}