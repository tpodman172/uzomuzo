package com.tpodman172.tsk2.server.context.taskProgress;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component //todo -> @Repository
public class TaskProgressRepository implements ITaskProgressRepository {

    @Autowired //todo -> remove field annotation
    private DSLContext jooq;

    @Override
    public void update(Long taskId, LocalDate targetDate, Boolean isCompleted) {

    }
//    @Override
//    public void update(Long taskId, Date targetDate, boolean isCompleted) {
//        byte b = 0x01;
//        jooq.insertInto(TASK_DAILY_PROGRESS, TASK_DAILY_PROGRESS.TASK_ID, TASK_DAILY_PROGRESS.TARGET_DATE, TASK_DAILY_PROGRESS.COMPLETED)
//                .values(taskId, targetDate, b).execute();
//    }
}
