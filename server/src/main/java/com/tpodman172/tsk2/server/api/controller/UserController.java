package com.tpodman172.tsk2.server.api.controller;

import com.tpodman172.tsk2.server.api.appService.UserAppService;
import com.tpodman172.tsk2.server.api.appService.model.PutUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserAppService userAppService;

    @Override
    public ResponseEntity<Void> putUser(@Valid PutUserDto putUserDto) {
        userAppService.createUser(putUserDto.getUserName(), putUserDto.getPassword());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
