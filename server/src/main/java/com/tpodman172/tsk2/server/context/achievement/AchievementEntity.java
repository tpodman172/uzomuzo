package com.tpodman172.tsk2.server.context.achievement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class AchievementEntity {
    private Long taskId;
    private LocalDate targetDate;
    private boolean isCompleted;
}
