package com.tpodman172.tsk2.server.context.exception;

public class ResourceConflictException extends DomainException {
    public ResourceConflictException(String message, Throwable e){
        super(message, e);
    }
}
