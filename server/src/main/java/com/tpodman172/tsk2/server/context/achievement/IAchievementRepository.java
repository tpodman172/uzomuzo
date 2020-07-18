package com.tpodman172.tsk2.server.context.achievement;

import java.time.LocalDate;
import java.util.List;

public interface IAchievementRepository {
    void update(AchievementEntity entity);

    List<AchievementEntity> findByTargetDateAndUserId(LocalDate localDate, Long userId);
}
