INSERT INTO `user` (`id`,`email`,`first_name`,`last_name`,`password`) VALUES ('4028b8814aee5780014aee5942110001','admin@admin.fr','admin','admin','$2a$10$8Pt0AkotOejCh24Fx1dImeBP3jIxdxvmkDnaODep5IUP1ASKSmplu');

INSERT INTO `role` (`id`,`name`) VALUES ('1','ROLE_ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES ('2','ROLE_USER');

INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES (SELECT id from `user` WHERE `email` = "admin@admin.fr",'1');