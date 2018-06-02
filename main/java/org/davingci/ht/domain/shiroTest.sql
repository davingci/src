INSERT INTO `permission` VALUES ('1', 1, '0', '0/', 'user:view','user management',  'menu', 'user/userList');
INSERT INTO `permission` VALUES ('2', 1, '1', '0/1', 'user:add','user add',  'button', 'user/userAdd');
INSERT INTO `permission` VALUES ('3', 1, '1', '0/1', 'user:del','user del',  'button', 'user/userDel');
INSERT INTO `Role` VALUES ('1', 1, 'administrator', 'admin');
INSERT INTO `Role` VALUES ('2', 1, 'member', 'member');
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');

INSERT INTO `user` VALUES ('1', 'administrator', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0, 'admin');
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1', '2');

