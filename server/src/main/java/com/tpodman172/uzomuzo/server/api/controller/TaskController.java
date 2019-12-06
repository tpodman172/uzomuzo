package com.tpodman172.uzomuzo.server.api.controller;

import com.tpodman172.uzomuzo.server.api.appService.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class TaskController implements TaskApi {

    @Override
    public ResponseEntity<List<Task>> listTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("はをみがく");
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("くつしたをはく");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        List<Task> list = List.of(task1, task2);
        return new ResponseEntity(list, header, HttpStatus.OK);
    }
}
