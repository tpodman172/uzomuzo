package com.tpodman172.tsk2.server.api.controller;

import com.tpodman172.tsk2.server.api.appService.UserAppService;
import com.tpodman172.tsk2.server.api.appService.model.PutUserDto;
import com.tpodman172.tsk2.server.base.authentication.JwtTokenUtil;
import com.tpodman172.tsk2.server.base.authentication.KeyConfig;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserAppService userAppService;

    private final KeyConfig keyConfig;

    @Override
    public ResponseEntity<Void> putUser(@Valid PutUserDto putUserDto) {
        val userId = userAppService.createUser(putUserDto.getUserName(), putUserDto.getPassword());
        val token = JwtTokenUtil.createJwtToken(keyConfig, userId, putUserDto.getUserName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
