package com.tpodman172.uzomuzo.server.api.appService;


import com.tpodman172.uzomuzo.server.api.appService.model.TaskDTO;
import com.tpodman172.uzomuzo.server.context.task.ITaskRepository;
import com.tpodman172.uzomuzo.server.context.task.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
@Transactional
public class TaskAppService {

    @Autowired
    private ITaskRepository taskRepository;

    public List<TaskDTO> fetchTasks() {
        return taskRepository.find().stream()
                .map(taskEntity -> new TaskDTO()
                        .id(taskEntity.getId())
                        .title(taskEntity.getTitle()))
                .collect(Collectors.toList());
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }
}
