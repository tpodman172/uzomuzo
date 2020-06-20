package com.tpodman172.tsk2.server.api.appService;


import com.tpodman172.tsk2.infra.schema.rds.tables.TaskProgress;
import com.tpodman172.tsk2.server.api.appService.model.TaskDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskProgressDTO;
import com.tpodman172.tsk2.server.context.task.ITaskRepository;
import com.tpodman172.tsk2.server.context.task.TaskEntity;
import com.tpodman172.tsk2.server.context.taskProgress.ITaskProgressRepository;
import com.tpodman172.tsk2.server.context.taskProgress.TaskProgressEntity;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
@Transactional
public class TaskAppService {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private ITaskProgressRepository taskProgressRepository;

    public List<TaskDTO> fetchTasks() {
        return taskRepository.find().stream()
                .map(taskEntity -> new TaskDTO()
                        .id(taskEntity.getId())
                        .title(taskEntity.getTitle()))
                .collect(Collectors.toList());
    }

    public List<TaskProgressDTO> fetchTaskProgress(LocalDate targetDate) {
        val taskProgressEntityMap = taskProgressRepository.findByTargetDate(targetDate)
                .stream()
                .collect(Collectors.toMap(TaskProgressEntity::getTaskId, Function.identity()));

        return taskRepository.findByIds(taskProgressEntityMap.keySet())
                .stream()
                .map(taskEntity -> mapToTaskProgressDTO(taskEntity, taskProgressEntityMap))
                .collect(Collectors.toList());
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }

    public void updateTaskProgress(Long taskId, boolean isCompleted) {
        taskProgressRepository.update(new TaskProgressEntity(taskId, LocalDate.now(), isCompleted));
        return;
    }

    private TaskProgressDTO mapToTaskProgressDTO(TaskEntity taskEntity, Map<Long, TaskProgressEntity> taskProgressEntityMap){
        val taskProgressDTO = new TaskProgressDTO();
        taskProgressDTO.setId(taskEntity.getId());
        taskProgressDTO.setTaskTitle(taskEntity.getTitle());
        taskProgressDTO.setCompleted(taskProgressEntityMap.get(taskEntity.getId()).isCompleted());
        taskProgressDTO.setTargetDate(taskProgressEntityMap.get(taskEntity.getId()).getTargetDate());
        return taskProgressDTO;
    }
}
