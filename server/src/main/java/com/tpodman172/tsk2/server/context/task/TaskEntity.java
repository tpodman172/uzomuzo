package com.tpodman172.tsk2.server.context.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TaskEntity {
    private final Long id;
    private final Long userId;

    private final String title;

    public static TaskEntity createNew(Long userId, String title) {
        return new TaskEntity(null, userId, title);
    }
}
