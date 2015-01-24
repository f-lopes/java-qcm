/*
-- Query: SELECT * FROM `java-qcm`.grade
-- Date: 2015-01-21 23:36
*/
INSERT INTO `grade` (`id`,`name`) VALUES ('1','B1');
INSERT INTO `grade` (`id`,`name`) VALUES ('2','B2');
INSERT INTO `grade` (`id`,`name`) VALUES ('3','L1');
INSERT INTO `grade` (`id`,`name`) VALUES ('4','EXP1');
INSERT INTO `grade` (`id`,`name`) VALUES ('5','EXP2');

/*
-- Query: SELECT * FROM `java-qcm`.role
-- Date: 2015-01-21 23:36
*/
INSERT INTO `role` (`id`,`name`) VALUES ('1','ROLE_ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES ('2','ROLE_USER');
INSERT INTO `role` (`id`,`name`) VALUES ('3','ROLE_STUDENT');
INSERT INTO `role` (`id`,`name`) VALUES ('4','ROLE_TEACHER');

/*
-- Query: SELECT * FROM `java-qcm`.user
-- Date: 2015-01-18 16:35
*/
INSERT INTO `user` (`id`,`email`,`first_name`,`last_name`,`password`) VALUES ('','admin@admin.fr','admin','admin','$2a$10$8Pt0AkotOejCh24Fx1dImeBP3jIxdxvmkDnaODep5IUP1ASKSmplu');

/*
-- Query: SELECT * FROM `java-qcm`.user_roles
-- Date: 2015-01-18 16:36
*/
INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES (SELECT id FROM user U WHERE U.email = "admin@admin.fr" ,'1');
