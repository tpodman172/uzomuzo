CREATE DATABASE IF NOT EXISTS tsk2 DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS tsk2.user
(
    user_id     BIGINT NOT NULL PRIMARY KEY,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(256) NOT NULL,
    created_at  TIMESTAMP NOT NULL default current_timestamp,
    updated_at  TIMESTAMP NOT NULL default current_timestamp on update current_timestamp
);

CREATE TABLE IF NOT EXISTS tsk2.task
(
    task_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT NOT NULL,
    title      VARCHAR(100) NOT NULL,
    cycle_type ENUM('DAIRY') NOT NULL default 'DAIRY',
    created_at TIMESTAMP NOT NULL default current_timestamp,
    updated_at TIMESTAMP NOT NULL default current_timestamp on update current_timestamp,
    FOREIGN KEY user_user_id_task_user_id (user_id)
    REFERENCES tsk2.user (user_id)
);

CREATE TABLE IF NOT EXISTS tsk2.achievement
(
    task_id     BIGINT    NOT NULL,
    target_date DATE      NOT NULL, -- weeklyのタスクができたら週の初めの日を入れればよい
    completed   BOOLEAN,
    created_at  TIMESTAMP NOT NULL default current_timestamp,
    updated_at  TIMESTAMP NOT NULL default current_timestamp on update current_timestamp,
    PRIMARY KEY (task_id, target_date),
    FOREIGN KEY task_task_id_achievement_task_id (task_id)
    REFERENCES tsk2.task (task_id)
);

