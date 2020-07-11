package com.tpodman172.tsk2.server.api.controller;

import com.tpodman172.tsk2.server.api.appService.AuthenticationAppService;
import com.tpodman172.tsk2.server.api.appService.model.LoginDTO;
import com.tpodman172.tsk2.server.base.exception.AuthenticationFailedException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {
    private final AuthenticationAppService appService;

    @Override
    public ResponseEntity<Void> postLogin(@Valid LoginDTO loginDTO) {
        HttpHeaders headers = new HttpHeaders();

        try {
            final val token = appService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "authorization");
            headers.add(HttpHeaders.AUTHORIZATION, token);
            return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
        } catch (AuthenticationFailedException e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
    }
}
