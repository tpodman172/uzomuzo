package com.tpodman172.tsk2.server.api.appService;


import com.tpodman172.tsk2.server.api.appService.model.TaskChallengeRecordDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskDTO;
import com.tpodman172.tsk2.server.context.task.ITaskRepository;
import com.tpodman172.tsk2.server.context.task.TaskEntity;
import com.tpodman172.tsk2.server.context.taskProgress.ITaskProgressRepository;
import com.tpodman172.tsk2.server.context.taskProgress.TaskProgressEntity;
import lombok.AllArgsConstructor;
import lombok.val;
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

    public TaskChallengeRecordDTO fetchTaskProgress(LocalDate targetDate) {
        val taskProgressEntityMap = taskProgressRepository.findByTargetDate(targetDate)
                .stream()
                .collect(Collectors.toMap(TaskProgressEntity::getTaskId, Function.identity()));

        val taskEntities = taskRepository.find().stream().collect(Collectors.toList());

        return convertToTaskProgressDTO(targetDate, taskEntities, taskProgressEntityMap);
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }

    public void updateTaskProgress(Long taskId, LocalDate targetDate, boolean isCompleted) {
        taskProgressRepository.update(new TaskProgressEntity(taskId, targetDate, isCompleted));
    }

    private TaskChallengeRecordDTO convertToTaskProgressDTO(LocalDate targetDate, List<TaskEntity> taskEntities, Map<Long, TaskProgressEntity> taskProgressEntityMap) {
        val taskProgressDTO = new TaskChallengeRecordDTO();
        taskProgressDTO.setTargetDate(targetDate);
        taskProgressDTO.setTasks(taskEntities
                .stream()
                .map(this::mapToTaskDTO)
                .collect(Collectors.toList()));
        taskProgressDTO.setCompletedTaskIds(taskProgressEntityMap.entrySet()
                .stream()
                .filter(map -> map.getValue().isCompleted())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
        return taskProgressDTO;
    }

    private TaskDTO mapToTaskDTO(TaskEntity taskEntity) {
        val taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        return taskDTO;
    }
}
