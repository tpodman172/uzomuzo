package com.tpodman172.uzomuzo.server.context.task;

import com.tpodman172.uzomuzo.infra.schema.rds.Tables;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TaskRepository implements ITaskRepository {

    @Autowired
    private DSLContext jooq;

    @Override
    public List<TaskEntity> find() {
        // そういえばコネクションのクローズはだれがやってくれるんだっけ…AOPか…
        Result<Record> result = jooq.select().from(Tables.TASKS).fetch();

        for (final Record r : result) {
            final Integer id = r.getValue(Tables.TASKS.ID);
            final String title = r.getValue(Tables.TASKS.TITLE);

            System.out.println(
                    "ID: " + id + " title: " + title);
        }

        return null;
    }
}
