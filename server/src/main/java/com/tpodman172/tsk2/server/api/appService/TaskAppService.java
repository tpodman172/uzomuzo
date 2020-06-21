package com.tpodman172.tsk2.server.api.appService;


import com.tpodman172.tsk2.server.api.appService.model.TaskChallengeResultDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskDTO;
import com.tpodman172.tsk2.server.context.task.ITaskRepository;
import com.tpodman172.tsk2.server.context.task.TaskEntity;
import com.tpodman172.tsk2.server.context.taskChallengeResult.ITaskChallengeResultRepository;
import com.tpodman172.tsk2.server.context.taskChallengeResult.TaskChallengeResultEntity;
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
    private ITaskChallengeResultRepository taskChallengeResultRepository;

    public List<TaskDTO> fetchTasks() {
        return taskRepository.find().stream()
                .map(taskEntity -> new TaskDTO()
                        .id(taskEntity.getId())
                        .title(taskEntity.getTitle()))
                .collect(Collectors.toList());
    }

    public TaskChallengeResultDTO fetchTaskChallengeResult(LocalDate targetDate) {
        val taskChallengeResultEntityMap = taskChallengeResultRepository.findByTargetDate(targetDate)
                .stream()
                .collect(Collectors.toMap(TaskChallengeResultEntity::getTaskId, Function.identity()));

        val taskEntities = taskRepository.find().stream().collect(Collectors.toList());

        return convertToTaskChallengeResultDTO(targetDate, taskEntities, taskChallengeResultEntityMap);
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }

    public void updateTaskChallengeResult(Long taskId, LocalDate targetDate, boolean isCompleted) {
        taskChallengeResultRepository.update(new TaskChallengeResultEntity(taskId, targetDate, isCompleted));
    }

    private TaskChallengeResultDTO convertToTaskChallengeResultDTO(LocalDate targetDate, List<TaskEntity> taskEntities, Map<Long, TaskChallengeResultEntity> taskChallengeResultEntityMap) {
        val taskChallengeResultDTO = new TaskChallengeResultDTO();
        taskChallengeResultDTO.setTargetDate(targetDate);
        taskChallengeResultDTO.setTasks(taskEntities
                .stream()
                .map(this::mapToTaskDTO)
                .collect(Collectors.toList()));
        taskChallengeResultDTO.setCompletedTaskIds(taskChallengeResultEntityMap.entrySet()
                .stream()
                .filter(map -> map.getValue().isCompleted())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
        return taskChallengeResultDTO;
    }

    private TaskDTO mapToTaskDTO(TaskEntity taskEntity) {
        val taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        return taskDTO;
    }
}
