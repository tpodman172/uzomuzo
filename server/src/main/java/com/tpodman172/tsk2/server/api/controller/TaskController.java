package com.tpodman172.tsk2.server.api.controller;

import com.tpodman172.tsk2.server.api.appService.TaskAppService;
import com.tpodman172.tsk2.server.api.appService.model.TaskChallengeRecordDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskCreateDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskDTO;
import com.tpodman172.tsk2.server.context.task.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

// todo error handling by AOP
@AllArgsConstructor
@RestController
public class TaskController implements TasksApi {

    TaskAppService taskAppService;

    @Override
    public ResponseEntity<Void> putTaskChallengeRecord(Long id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull @Valid LocalDate targetDate, @NotNull @Valid Boolean completed) {
        taskAppService.updateTaskProgress(id, targetDate, completed);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasks() {
//        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        List<TaskDTO> tasks = taskAppService.fetchTasks();

        return new ResponseEntity(tasks, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskChallengeRecordDTO> getTaskChallengeRecord(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity(taskAppService.fetchTaskProgress(date), null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> postTask(@Valid TaskCreateDTO taskCreateDTO) {
        return new ResponseEntity(taskAppService.createTask(new TaskEntity(null, taskCreateDTO.getTitle())), null, HttpStatus.OK);
    }


}
