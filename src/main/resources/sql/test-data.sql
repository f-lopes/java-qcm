DROP TABLE answer IF EXISTS CASCADE;
DROP TABLE course IF EXISTS CASCADE;
DROP TABLE evaluation IF EXISTS CASCADE;
DROP TABLE evaluation_student IF EXISTS CASCADE;
DROP TABLE grade IF EXISTS CASCADE;
DROP TABLE qcm IF EXISTS CASCADE;
DROP TABLE question IF EXISTS CASCADE;
DROP TABLE role IF EXISTS CASCADE;
DROP TABLE student IF EXISTS CASCADE;
DROP TABLE teacher IF EXISTS CASCADE;
DROP TABLE user IF EXISTS CASCADE;
DROP TABLE user_roles IF EXISTS CASCADE;

CREATE TABLE answer
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  answer_rate INT NOT NULL,
  content VARCHAR(255),
  correct BIT NOT NULL,
  question_id VARCHAR(255)
);
CREATE TABLE course
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);
CREATE TABLE evaluation
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  end_date DATETIME,
  start_date DATETIME,
  course_id VARCHAR(255),
  grade_id VARCHAR(255),
  qcm_id VARCHAR(255),
  teacher_teacher_id VARCHAR(255)
);
CREATE TABLE evaluation_student
(
  evaluationid VARCHAR(255) NOT NULL,
  studentid VARCHAR(255) NOT NULL,
  date DATETIME,
  mark INT NOT NULL,
  evaluation_id VARCHAR(255),
  student_student_id VARCHAR(255),
  student_id VARCHAR(255),
  PRIMARY KEY (evaluationid, studentid)
);
CREATE TABLE grade
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);
CREATE TABLE qcm
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);
CREATE TABLE question
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  label VARCHAR(255),
  points INT NOT NULL,
  qcm_id VARCHAR(255)
);
CREATE TABLE role
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  name VARCHAR(20)
);
CREATE TABLE student
(
  student_id VARCHAR(255) PRIMARY KEY NOT NULL,
  grade_id VARCHAR(255)
);
CREATE TABLE teacher
(
  speciality VARCHAR(255),
  teacher_id VARCHAR(255) PRIMARY KEY NOT NULL
);
CREATE TABLE user
(
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  email VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  password VARCHAR(255)
);
CREATE TABLE user_roles
(
  user_id VARCHAR(255) NOT NULL,
  role_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (user_id, role_id)
);
ALTER TABLE answer ADD FOREIGN KEY (question_id) REFERENCES question (id);
CREATE INDEX FK_eix9du6u2r4wxwu415wq8yb99 ON answer (question_id);
ALTER TABLE evaluation ADD FOREIGN KEY (qcm_id) REFERENCES qcm (id);
ALTER TABLE evaluation ADD FOREIGN KEY (course_id) REFERENCES course (id);
ALTER TABLE evaluation ADD FOREIGN KEY (teacher_teacher_id) REFERENCES teacher (teacher_id);
ALTER TABLE evaluation ADD FOREIGN KEY (grade_id) REFERENCES grade (id);
CREATE INDEX FK_57mjenx293jkb9jvudbdtt5bp ON evaluation (qcm_id);
CREATE INDEX FK_84o5rl9plg3v1unbn1gqd8u66 ON evaluation (course_id);
CREATE INDEX FK_aa6q8nkyylitbrverxsi2oe1r ON evaluation (teacher_teacher_id);
CREATE INDEX FK_kfam1k0ihfgs45j53c0r4xdsq ON evaluation (grade_id);
ALTER TABLE evaluation_student ADD FOREIGN KEY (evaluation_id) REFERENCES evaluation (id);
ALTER TABLE evaluation_student ADD FOREIGN KEY (student_student_id) REFERENCES student (student_id);
ALTER TABLE evaluation_student ADD FOREIGN KEY (student_id) REFERENCES student (student_id);
CREATE INDEX FK_8l0itw4t0fi10qv1ekbu3gbwh ON evaluation_student (evaluation_id);
CREATE INDEX FK_9ulyk975qer8mqspdk00wuexw ON evaluation_student (student_student_id);
CREATE INDEX FK_trhltg8i77kbr0srjk2j0nb3o ON evaluation_student (student_id);
ALTER TABLE question ADD FOREIGN KEY (qcm_id) REFERENCES qcm (id);
CREATE INDEX FK_lnspdlo4u3ji98bwdr46h5e5f ON question (qcm_id);
ALTER TABLE student ADD FOREIGN KEY (grade_id) REFERENCES grade (id);
ALTER TABLE student ADD FOREIGN KEY (student_id) REFERENCES user (id);
CREATE INDEX FK_j0r0ce460fg07mg08xhgdgyaw ON student (grade_id);
ALTER TABLE teacher ADD FOREIGN KEY (teacher_id) REFERENCES user (id);
CREATE UNIQUE INDEX UK_ob8kqyqqgmefl0aco34akdtpe ON user (email);
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES user (id);
CREATE INDEX FK_5q4rc4fh1on6567qk69uesvyf ON user_roles (role_id);

INSERT INTO role (id, name) VALUES ('4028818b4b22968e014b22975a260000', 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES ('4028818b4b22968e014b22975abd0001', 'ROLE_STUDENT');
INSERT INTO role (id, name) VALUES ('4028818b4b22968e014b22975abd0002', 'ROLE_TEACHER');

INSERT INTO grade (id, name) VALUES ('4028818b4b22968e014b2297b4680003', 'L3');
INSERT INTO grade (id, name) VALUES ('4028818b4b22968e014b2297b4690004', 'B2');
INSERT INTO grade (id, name) VALUES ('4028818b4b22968e014b2297b4690005', 'EXP2');
INSERT INTO grade (id, name) VALUES ('4028818b4b22968e014b2297b46a0006', 'EXP1');
INSERT INTO grade (id, name) VALUES ('4028818b4b22968e014b2297b46b0007', 'B1');

INSERT INTO user (id, email, first_name, last_name, password) VALUES ('4028818b4b22968e014b2297cb6b0009', 'admin@admin.fr', 'admin', 'admin', '$2a$10$ug0BeBD1H/Oxq5NTVhYR5unIl8cHazcPNiaCByqOb/EC3gKuKPGWS');
INSERT INTO user (id, email, first_name, last_name, password) VALUES ('4028818b4b27c5c8014b27d2580f0002', 'student@student.fr', null, null, '$2a$10$4eOYF/R2NXmXnyhnpm2maemcbK/2Yhp3481AdRLv2o.7w0Fa6Rf9m');
INSERT INTO user (id, email, first_name, last_name, password) VALUES ('4028818b4b27e139014b27ea63310000', 'teacher@teacher.fr', null, null, '$2a$10$MiF6LQGl1D/ItYZw3whI8uosIdA6.Z7rAJlgjh.Nqhm0iIpEUC8h2');

INSERT INTO student (student_id, grade_id) VALUES ('4028818b4b27c5c8014b27d2580f0002', '4028818b4b22968e014b2297b4680003');

INSERT INTO teacher (speciality, teacher_id) VALUES (null, '4028818b4b27e139014b27ea63310000');

INSERT INTO user_roles (user_id, role_id) VALUES ('4028818b4b22968e014b2297cb6b0009', '4028818b4b22968e014b22975a260000');
INSERT INTO user_roles (user_id, role_id) VALUES ('4028818b4b27c5c8014b27d2580f0002', '4028818b4b22968e014b22975abd0001');
INSERT INTO user_roles (user_id, role_id) VALUES ('4028818b4b27e139014b27ea63310000', '4028818b4b22968e014b22975abd0002');

INSERT INTO course (id, name) VALUES ('4028818b4b22968e014b2297ba010008', 'Java');

INSERT INTO qcm (id, name) VALUES ('4028818b4b27c5c8014b27cf7f100000', 'TestQcm');
INSERT INTO qcm (id, name) VALUES ('4028818e4bc75b7b014bc77f0c8d0001', 'wtrwer');
INSERT INTO qcm (id, name) VALUES ('4028b8814b363411014b3634b3fc0000', 'test');

INSERT INTO evaluation (id, end_date, start_date, course_id, grade_id, qcm_id, teacher_teacher_id) VALUES ('4028818b4b27db35014b27e0486f0000', '2015-02-28 00:00:00', '2015-01-25 00:00:00', '4028818b4b22968e014b2297ba010008', '4028818b4b22968e014b2297b4680003', '4028818b4b27c5c8014b27cf7f100000', '4028818b4b27e139014b27ea63310000');
INSERT INTO evaluation (id, end_date, start_date, course_id, grade_id, qcm_id, teacher_teacher_id) VALUES ('4028818e4bc75b7b014bc7713cde0000', '2016-01-01 00:00:00', '2015-01-01 00:00:00', '4028818b4b22968e014b2297ba010008', '4028818b4b22968e014b2297b4680003', null, '4028818b4b27e139014b27ea63310000');

INSERT INTO evaluation_student (evaluationid, studentid, date, mark, evaluation_id, student_student_id, student_id) VALUES ('4028818b4b27db35014b27e0486f0000', '4028818b4b27c5c8014b27d2580f0002', '2015-02-06 22:46:53', 1, '4028818b4b27db35014b27e0486f0000', '4028818b4b27c5c8014b27d2580f0002', null);

INSERT INTO question (id, label, points, qcm_id) VALUES ('4028818b4b27e139014b280f6a060001', 'test', 1, '4028818b4b27c5c8014b27cf7f100000');

INSERT INTO answer (id, answer_rate, content, correct, question_id) VALUES ('4028818b4b27e139014b280f6a060052', 1, 'TestAnswer', true, '4028818b4b27e139014b280f6a060001');
