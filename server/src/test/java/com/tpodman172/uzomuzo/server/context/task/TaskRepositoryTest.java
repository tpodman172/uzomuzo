package com.tpodman172.uzomuzo.server.context.task;

import com.tpodman172.uzomuzo.infra.schema.rds.tables.records.TasksRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.tpodman172.uzomuzo.infra.schema.rds.tables.Tasks.TASKS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private ITaskRepository taskRepository;


    @Autowired
    private DSLContext jooq;

    @Test
    void fetch() throws Exception {
        taskRepository.find();
        assertThat(true);
    }

    @Test
    void create() throws Exception {
        final Long taskId = taskRepository.create(new TaskEntity(null, "testTitle"));

        final TasksRecord tasksRecord = jooq.selectFrom(TASKS)
                .where(TASKS.ID.eq(taskId))
                .fetchOne().into(TASKS);

        assert (tasksRecord.getTitle()).equals("testTitle");
    }
}