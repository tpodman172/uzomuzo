package com.tpodman172.tsk2.server.context.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskEntity {
    private Long id;
    private String title;
}
