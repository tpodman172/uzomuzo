package com.tpodman172.tsk2.server.base.aspect;

import com.tpodman172.tsk2.server.context.exception.ResourceConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandlingInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Void> handlingResourceConflictException() {
        return new ResponseEntity(null, HttpStatus.CONFLICT);
    }
}
