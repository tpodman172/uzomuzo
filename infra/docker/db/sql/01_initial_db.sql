CREATE DATABASE IF NOT EXISTS tsk2 DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS tsk2.tasks(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  created_at TIMESTAMP NOT NULL default current_timestamp,
  updated_at TIMESTAMP NOT NULL default current_timestamp on update current_timestamp
);