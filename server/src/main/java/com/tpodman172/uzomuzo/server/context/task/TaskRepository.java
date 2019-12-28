package com.tpodman172.uzomuzo.server.context.task;

import com.tpodman172.uzomuzo.infra.schema.rds.Tables;
import org.jooq.DSLContext;
import org.jooq.Record;
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
                .fetchStream()
                .map(record -> new TaskEntity(
                        record.get(Tables.TASKS.ID).longValue(),
                        record.get(Tables.TASKS.TITLE)))
                .collect(Collectors.toList());

        return taskList;
    }
}
