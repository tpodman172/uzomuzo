package com.tpodman172.tsk2.server.context.taskChallengeResult;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class TaskChallengeResultEntity {
    private Long taskId;
    private LocalDate targetDate;
    private boolean isCompleted;
}
