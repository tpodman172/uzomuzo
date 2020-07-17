package com.tpodman172.tsk2.server.context.exception;

public class DomainException extends RuntimeException{
    public DomainException(){
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable e) {
        super(message, e);
    }
}
