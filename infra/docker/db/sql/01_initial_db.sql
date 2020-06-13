CREATE DATABASE IF NOT EXISTS tsk2 DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS tsk2.task
( -- todo add column cycle type
    task_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    title      VARCHAR(100),
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp on update current_timestamp
);

CREATE TABLE IF NOT EXISTS tsk2.completed_daily_task
(
    task_id     BIGINT    NOT NULL,
    target_date DATE      NOT NULL,
    created_at  TIMESTAMP NOT NULL default current_timestamp,
    updated_at  TIMESTAMP NOT NULL default current_timestamp on update current_timestamp,
    PRIMARY KEY (task_id, target_date),
    FOREIGN KEY task_task_id_completed_daily_task_task_id (task_id)
    REFERENCES tsk2.task (task_id)
);
