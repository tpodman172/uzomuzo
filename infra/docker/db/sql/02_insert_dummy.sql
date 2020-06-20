USE tsk2;
-- Insert initlal data
SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;
START TRANSACTION;
INSERT INTO task(title, cycle_type) VALUES('きがえる', 'DAIRY');
INSERT INTO task(title, cycle_type) VALUES('あさごはんをたべる', 'DAIRY');
INSERT INTO task(title, cycle_type) VALUES('しょっきをはこぶ', 'DAIRY');
INSERT INTO task(title, cycle_type) VALUES('といれにいく', 'DAIRY');
INSERT INTO task(title, cycle_type) VALUES('くつしたをはく', 'DAIRY');
INSERT INTO task(title, cycle_type) VALUES('ほいくえんにいく', 'DAIRY');
COMMIT;