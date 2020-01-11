/*
 * This file is generated by jOOQ.
 */
package com.tpodman172.uzomuzo.infra.schema.rds;


import com.tpodman172.uzomuzo.infra.schema.rds.tables.Tasks;
import com.tpodman172.uzomuzo.infra.schema.rds.tables.records.TasksRecord;

import javax.annotation.processing.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>tsk2</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TasksRecord, Long> IDENTITY_TASKS = Identities0.IDENTITY_TASKS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TasksRecord> KEY_TASKS_PRIMARY = UniqueKeys0.KEY_TASKS_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<TasksRecord, Long> IDENTITY_TASKS = Internal.createIdentity(Tasks.TASKS, Tasks.TASKS.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<TasksRecord> KEY_TASKS_PRIMARY = Internal.createUniqueKey(Tasks.TASKS, "KEY_tasks_PRIMARY", Tasks.TASKS.ID);
    }
}
