/*
 * This file is generated by jOOQ.
 */
package com.tpodman172.tsk2.infra.schema.rds.tables;


import com.tpodman172.tsk2.infra.schema.rds.Indexes;
import com.tpodman172.tsk2.infra.schema.rds.Keys;
import com.tpodman172.tsk2.infra.schema.rds.Tsk2;
import com.tpodman172.tsk2.infra.schema.rds.enums.TaskCycleType;
import com.tpodman172.tsk2.infra.schema.rds.tables.records.TaskRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Task extends TableImpl<TaskRecord> {

    private static final long serialVersionUID = 1935239777;

    /**
     * The reference instance of <code>tsk2.task</code>
     */
    public static final Task TASK = new Task();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TaskRecord> getRecordType() {
        return TaskRecord.class;
    }

    /**
     * The column <code>tsk2.task.task_id</code>.
     */
    public final TableField<TaskRecord, Long> TASK_ID = createField(DSL.name("task_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>tsk2.task.user_id</code>.
     */
    public final TableField<TaskRecord, Long> USER_ID = createField(DSL.name("user_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>tsk2.task.title</code>.
     */
    public final TableField<TaskRecord, String> TITLE = createField(DSL.name("title"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>tsk2.task.cycle_type</code>.
     */
    public final TableField<TaskRecord, TaskCycleType> CYCLE_TYPE = createField(DSL.name("cycle_type"), org.jooq.impl.SQLDataType.VARCHAR(5).nullable(false).defaultValue(org.jooq.impl.DSL.inline("DAIRY", org.jooq.impl.SQLDataType.VARCHAR)).asEnumDataType(com.tpodman172.tsk2.infra.schema.rds.enums.TaskCycleType.class), this, "");

    /**
     * The column <code>tsk2.task.created_at</code>.
     */
    public final TableField<TaskRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>tsk2.task.updated_at</code>.
     */
    public final TableField<TaskRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>tsk2.task</code> table reference
     */
    public Task() {
        this(DSL.name("task"), null);
    }

    /**
     * Create an aliased <code>tsk2.task</code> table reference
     */
    public Task(String alias) {
        this(DSL.name(alias), TASK);
    }

    /**
     * Create an aliased <code>tsk2.task</code> table reference
     */
    public Task(Name alias) {
        this(alias, TASK);
    }

    private Task(Name alias, Table<TaskRecord> aliased) {
        this(alias, aliased, null);
    }

    private Task(Name alias, Table<TaskRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Task(Table<O> child, ForeignKey<O, TaskRecord> key) {
        super(child, key, TASK);
    }

    @Override
    public Schema getSchema() {
        return Tsk2.TSK2;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TASK_USER_USER_ID_TASK_USER_ID);
    }

    @Override
    public Identity<TaskRecord, Long> getIdentity() {
        return Keys.IDENTITY_TASK;
    }

    @Override
    public UniqueKey<TaskRecord> getPrimaryKey() {
        return Keys.KEY_TASK_PRIMARY;
    }

    @Override
    public List<UniqueKey<TaskRecord>> getKeys() {
        return Arrays.<UniqueKey<TaskRecord>>asList(Keys.KEY_TASK_PRIMARY);
    }

    @Override
    public List<ForeignKey<TaskRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TaskRecord, ?>>asList(Keys.TASK_IBFK_1);
    }

    public User user() {
        return new User(this, Keys.TASK_IBFK_1);
    }

    @Override
    public Task as(String alias) {
        return new Task(DSL.name(alias), this);
    }

    @Override
    public Task as(Name alias) {
        return new Task(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(String name) {
        return new Task(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(Name name) {
        return new Task(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, String, TaskCycleType, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
