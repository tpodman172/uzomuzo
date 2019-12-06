USE tsk2;
-- Insert initlal data
SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;
START TRANSACTION;
INSERT INTO tasks(title) VALUES('きがえる');
INSERT INTO tasks(title) VALUES('あさごはんをたべる');
INSERT INTO tasks(title) VALUES('しょっきをはこぶ');
INSERT INTO tasks(title) VALUES('といれにいく');
INSERT INTO tasks(title) VALUES('くつしたをはく');
INSERT INTO tasks(title) VALUES('ほいくえんにいく');
COMMIT;