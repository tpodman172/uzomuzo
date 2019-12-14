package com.tpodman172.uzomuzo.server.context.task;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private ITaskRepository taskRepository;

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

}