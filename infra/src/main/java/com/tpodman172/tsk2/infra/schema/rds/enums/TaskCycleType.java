/*
 * This file is generated by jOOQ.
 */
package com.tpodman172.tsk2.infra.schema.rds.enums;


import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum TaskCycleType implements EnumType {

    DAIRY("DAIRY");

    private final String literal;

    private TaskCycleType(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public String getName() {
        return "task_cycle_type";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
