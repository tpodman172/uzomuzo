package com.tpodman172.uzomuzo.server.context.task;

import org.jooq.DSLContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private ITaskRepository taskRepository;


    @Autowired
    private DSLContext jooq;

    @Test
    @DisplayName("2 * 3 = 6 であること😆")
    void test() throws Exception {
        assertThat(2 * 3).isEqualTo(6);
    }

    @Test
    void fetch() throws Exception {
        taskRepository.find();
        assertThat(true);
    }

    @Test
    void create() throws Exception {
        final Long taskId = taskRepository.create(new TaskEntity(null, "testTitle"));

        taskRepository.find();
        assertThat(true);
//        final Record record = jooq.selectFrom(TASKS)
//                .where(TASKS.ID.eq(taskId))
//                .fetchOne();
//        assert (record.get(TASKS.ID)).equals(taskId);
    }

}