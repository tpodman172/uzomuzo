package com.tpodman172.tsk2.server.api.appService;


import com.tpodman172.tsk2.server.api.appService.model.AchievementDTO;
import com.tpodman172.tsk2.server.api.appService.model.TaskDTO;
import com.tpodman172.tsk2.server.context.task.ITaskRepository;
import com.tpodman172.tsk2.server.context.task.TaskEntity;
import com.tpodman172.tsk2.server.context.achievement.IAchievementRepository;
import com.tpodman172.tsk2.server.context.achievement.AchievementEntity;
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
    private IAchievementRepository achievementRepository;

    public List<TaskDTO> fetchTasks() {
        return taskRepository.find().stream()
                .map(taskEntity -> new TaskDTO()
                        .id(taskEntity.getId())
                        .title(taskEntity.getTitle()))
                .collect(Collectors.toList());
    }

    public List<AchievementDTO> fetchAchievement(LocalDate targetDate) {
        return achievementRepository.findByTargetDate(targetDate)
                .stream()
                .map(this::mapToAchievementDTO)
                .collect(Collectors.toList());
    }

    public Long createTask(TaskEntity taskEntity) {
        return taskRepository.create(taskEntity);
    }

    public void updateAchievement(Long taskId, LocalDate targetDate, boolean isCompleted) {
        achievementRepository.update(new AchievementEntity(taskId, targetDate, isCompleted));
    }

    private AchievementDTO mapToAchievementDTO(AchievementEntity entity) {
        final val taskChallengeResultDTO = new AchievementDTO();
        taskChallengeResultDTO.setTaskId(entity.getTaskId());
        taskChallengeResultDTO.setTargetDate(entity.getTargetDate());
        taskChallengeResultDTO.setCompleted(entity.isCompleted());
        return taskChallengeResultDTO;
    }
}
