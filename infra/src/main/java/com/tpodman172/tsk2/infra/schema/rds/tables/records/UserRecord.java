/*
 * This file is generated by jOOQ.
 */
package com.tpodman172.tsk2.infra.schema.rds.tables.records;


import com.tpodman172.tsk2.infra.schema.rds.tables.User;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record5<Long, String, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -367563017;

    /**
     * Setter for <code>tsk2.user.user_id</code>.
     */
    public void setUserId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>tsk2.user.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>tsk2.user.user_name</code>.
     */
    public void setUserName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tsk2.user.user_name</code>.
     */
    public String getUserName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tsk2.user.password</code>.
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tsk2.user.password</code>.
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tsk2.user.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>tsk2.user.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>tsk2.user.updated_at</code>.
     */
    public void setUpdatedAt(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>tsk2.user.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return User.USER.USER_ID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.USER_NAME;
    }

    @Override
    public Field<String> field3() {
        return User.USER.PASSWORD;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return User.USER.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return User.USER.UPDATED_AT;
    }

    @Override
    public Long component1() {
        return getUserId();
    }

    @Override
    public String component2() {
        return getUserName();
    }

    @Override
    public String component3() {
        return getPassword();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component5() {
        return getUpdatedAt();
    }

    @Override
    public Long value1() {
        return getUserId();
    }

    @Override
    public String value2() {
        return getUserName();
    }

    @Override
    public String value3() {
        return getPassword();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value5() {
        return getUpdatedAt();
    }

    @Override
    public UserRecord value1(Long value) {
        setUserId(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setUserName(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserRecord value4(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public UserRecord value5(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public UserRecord values(Long value1, String value2, String value3, LocalDateTime value4, LocalDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Long userId, String userName, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(User.USER);

        set(0, userId);
        set(1, userName);
        set(2, password);
        set(3, createdAt);
        set(4, updatedAt);
    }
}
