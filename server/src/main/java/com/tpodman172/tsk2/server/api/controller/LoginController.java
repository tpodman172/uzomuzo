package com.tpodman172.tsk2.server.api.controller;

import com.tpodman172.tsk2.server.api.appService.model.LoginDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController implements LoginApi {
    @Override
    public ResponseEntity<Void> postLogin(@Valid LoginDTO loginDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "authorization");
        headers.add(HttpHeaders.AUTHORIZATION, "hello-jwt-token");
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
