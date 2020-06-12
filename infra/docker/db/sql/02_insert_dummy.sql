USE tsk2;
-- Insert initlal data
SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;
START TRANSACTION;
INSERT INTO task(title) VALUES('きがえる');
INSERT INTO task(title) VALUES('あさごはんをたべる');
INSERT INTO task(title) VALUES('しょっきをはこぶ');
INSERT INTO task(title) VALUES('といれにいく');
INSERT INTO task(title) VALUES('くつしたをはく');
INSERT INTO task(title) VALUES('ほいくえんにいく');
COMMIT;