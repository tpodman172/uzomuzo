CREATE DATABASE IF NOT EXISTS tsk2 DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS tsk2.task(
  task_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  created_at TIMESTAMP NOT NULL default current_timestamp,
  updated_at TIMESTAMP NOT NULL default current_timestamp on update current_timestamp
);

CREATE TABLE IF NOT EXISTS tsk2.challenge_history(
  challenge_history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  task_id BIGINT NOT NULL,
  challenge_date DATE NOT NULL,
  created_at TIMESTAMP NOT NULL default current_timestamp,
  updated_at TIMESTAMP NOT NULL default current_timestamp on update current_timestamp
)