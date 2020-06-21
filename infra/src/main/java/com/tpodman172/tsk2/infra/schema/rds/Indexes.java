/*
 * This file is generated by jOOQ.
 */
package com.tpodman172.tsk2.infra.schema.rds;


import com.tpodman172.tsk2.infra.schema.rds.tables.Task;
import com.tpodman172.tsk2.infra.schema.rds.tables.TaskChallengeResult;

import javax.annotation.processing.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>tsk2</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index TASK_PRIMARY = Indexes0.TASK_PRIMARY;
    public static final Index TASK_CHALLENGE_RESULT_PRIMARY = Indexes0.TASK_CHALLENGE_RESULT_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index TASK_PRIMARY = Internal.createIndex("PRIMARY", Task.TASK, new OrderField[] { Task.TASK.TASK_ID }, true);
        public static Index TASK_CHALLENGE_RESULT_PRIMARY = Internal.createIndex("PRIMARY", TaskChallengeResult.TASK_CHALLENGE_RESULT, new OrderField[] { TaskChallengeResult.TASK_CHALLENGE_RESULT.TASK_ID, TaskChallengeResult.TASK_CHALLENGE_RESULT.TARGET_DATE }, true);
    }
}
