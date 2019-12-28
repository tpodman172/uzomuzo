package com.tpodman172.uzomuzo.server.api.appService;


import com.tpodman172.uzomuzo.server.api.appService.model.Task;
import com.tpodman172.uzomuzo.server.context.task.ITaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class TaskAppService {

    @Autowired
    private ITaskRepository taskRepository;

    public List<Task> fetchTasks() {
        return taskRepository.find().stream()
                .map(taskEntity -> new Task()
                        .id(taskEntity.getId())
                        .title(taskEntity.getTitle()))
                .collect(Collectors.toList());
    }
}
