package com.tpodman172.tsk2.server.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class HealthController implements HealthApi {

    @Override
    public ResponseEntity<Void> healthCheck() {
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
