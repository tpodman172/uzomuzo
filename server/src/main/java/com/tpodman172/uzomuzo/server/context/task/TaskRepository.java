package com.tpodman172.uzomuzo.server.context.task;

import com.tpodman172.uzomuzo.infra.schema.rds.eden.Tables;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;


@Component
public class TaskRepository implements ITaskRepository {

    @Override
    public List<TaskEntity> find() {
        final String userName = "root";
        final String password = "root";
        final String url = "jdbc:mysql://localhost:3306/tsk2";

        try (Connection conn = DriverManager
                .getConnection(url, userName, password);
             final DSLContext create = DSL.using(conn, SQLDialect.MYSQL)) {
            Result<Record> result = create.select().from(Tables.TASKS).fetch();

            for (final Record r : result) {
                final Integer id = r.getValue(Tables.TASKS.ID);
                final String title = r.getValue(Tables.TASKS.TITLE);

                System.out.println(
                        "ID: " + id + " title: " + title);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
