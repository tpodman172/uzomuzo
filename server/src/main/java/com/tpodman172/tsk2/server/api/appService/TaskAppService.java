package com.tpodman172.tsk2.server.api.appService;


import com.tpodman172.tsk2.server.api.appService.model.TaskChallengeRecordDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskDTO;
import com.tpodman172.tsk2.server.context.task.ITaskRepository;
import com.tpodman172.tsk2.server.context.task.TaskEntity;
import com.tpodman172.tsk2.server.context.taskChallengeRecord.ITaskChallengeRecordRepository;
import com.tpodman172.tsk2.server.context.taskChallengeRecord.TaskChallengeRecordEntity;
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
    private ITaskChallengeRecordRepository taskChallengeRecordRepository;

    public List<TaskDTO> fetchTasks() {
        return taskRepository.find().stream()
                .map(taskEntity -> new TaskDTO()
                        .id(taskEntity.getId())
                        .title(taskEntity.getTitle()))
                .collect(Collectors.toList());
    }

    public TaskChallengeRecordDTO fetchTaskChallengeRecord(LocalDate targetDate) {
        val taskChallengeRecordEntityMap = taskChallengeRecordRepository.findByTargetDate(targetDate)
                .stream()
                .collect(Collectors.toMap(TaskChallengeRecordEntity::getTaskId, Function.identity()));

        val taskEntities = taskRepository.find().stream().collect(Collectors.toList());

        return convertToTaskChallengeRecordDTO(targetDate, taskEntities, taskChallengeRecordEntityMap);
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }

    public void updateTaskChallengeRecord(Long taskId, LocalDate targetDate, boolean isCompleted) {
        taskChallengeRecordRepository.update(new TaskChallengeRecordEntity(taskId, targetDate, isCompleted));
    }

    private TaskChallengeRecordDTO convertToTaskChallengeRecordDTO(LocalDate targetDate, List<TaskEntity> taskEntities, Map<Long, TaskChallengeRecordEntity> taskChallengeRecordEntityMap) {
        val taskChallengeRecordDTO = new TaskChallengeRecordDTO();
        taskChallengeRecordDTO.setTargetDate(targetDate);
        taskChallengeRecordDTO.setTasks(taskEntities
                .stream()
                .map(this::mapToTaskDTO)
                .collect(Collectors.toList()));
        taskChallengeRecordDTO.setCompletedTaskIds(taskChallengeRecordEntityMap.entrySet()
                .stream()
                .filter(map -> map.getValue().isCompleted())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
        return taskChallengeRecordDTO;
    }

    private TaskDTO mapToTaskDTO(TaskEntity taskEntity) {
        val taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        return taskDTO;
    }
}
