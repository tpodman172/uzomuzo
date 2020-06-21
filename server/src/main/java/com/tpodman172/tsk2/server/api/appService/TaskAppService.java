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

    public List<TaskChallengeResultDTO> fetchTaskChallengeResult(LocalDate targetDate) {
        return taskChallengeResultRepository.findByTargetDate(targetDate)
                .stream()
                .map(this::mapToTaskChallengeResultDTO)
                .collect(Collectors.toList());
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }

    public void updateTaskChallengeResult(Long taskId, LocalDate targetDate, boolean isCompleted) {
        taskChallengeResultRepository.update(new TaskChallengeResultEntity(taskId, targetDate, isCompleted));
    }

    private TaskChallengeResultDTO mapToTaskChallengeResultDTO(TaskChallengeResultEntity entity) {
        final val taskChallengeResultDTO = new TaskChallengeResultDTO();
        taskChallengeResultDTO.setTaskId(entity.getTaskId());
        taskChallengeResultDTO.setTargetDate(entity.getTargetDate());
        taskChallengeResultDTO.setCompleted(entity.isCompleted());
        return taskChallengeResultDTO;
    }
}
