USE tsk2;
-- Insert initlal data
SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;
START TRANSACTION;
INSERT INTO user(user_name, password)
VALUES ('user', '$2a$10$rPwqW8yxLztYPp.yZ9WlWuNcnKdr1SkMXt9w6y0RF6CcVJUFWQ1.W');
INSERT INTO task(title, user_id, cycle_type)
VALUES ('きがえる', 1, 'DAIRY');
INSERT INTO task(title, user_id, cycle_type)
VALUES ('あさごはんをたべる', 1, 'DAIRY');
INSERT INTO task(title, user_id, cycle_type)
VALUES ('しょっきをはこぶ', 1, 'DAIRY');
INSERT INTO task(title, user_id, cycle_type)
VALUES ('といれにいく', 1, 'DAIRY');
INSERT INTO task(title, user_id, cycle_type)
VALUES ('くつしたをはく', 1, 'DAIRY');
INSERT INTO task(title, user_id, cycle_type) VALUES('ほいくえんにいく', 1, 'DAIRY');
COMMIT;