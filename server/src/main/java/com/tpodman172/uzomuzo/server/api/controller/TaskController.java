package com.tpodman172.uzomuzo.server.api.controller;

import com.tpodman172.uzomuzo.server.api.appService.TaskAppService;
import com.tpodman172.uzomuzo.server.api.appService.model.TaskCreateDTO;
import com.tpodman172.uzomuzo.server.api.appService.model.TaskDTO;
import com.tpodman172.uzomuzo.server.context.task.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class TaskController implements TaskApi {

    @Autowired
    TaskAppService taskAppService;

    @Override
    public ResponseEntity<List<TaskDTO>> tasksGet() {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        List<TaskDTO> tasks = taskAppService.fetchTasks();

        return new ResponseEntity(tasks, header, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> taskPost(@Valid TaskCreateDTO taskCreateDTO) {
        return new ResponseEntity(taskAppService.createTask(new TaskEntity(null, taskCreateDTO.getTitle())), HttpStatus.OK);
    }
}
