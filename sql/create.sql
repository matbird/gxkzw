
CREATE DATABASE IF NOT EXISTS `gxkzw`;

USE `gxkzw`;

/*
  创建账户表
*/
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`idNumber` varchar(50) NOT NULL COMMENT '身份证号',
	`realName` varchar(32) NOT NULL COMMENT '真实姓名',
	`userName` varchar(150) COMMENT '邮箱:网站注册时需要用到邮箱',
	`phone` varchar(50) COMMENT '手机号:移动端注册时需要',
	`password` varchar(150) NOT NULL,
	`salt` varchar(150) NOT NULL COMMENT '密码加盐',
	`status` int(11) NOT NULL COMMENT '账户状态:0 未激活,1 正常 -1 被锁定',	
	`createdAt` datetime NOT NULL,
	`ip` varchar(100) DEFAULT NULL COMMENT '账户注册时的ip',
	`avatar` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` varchar(33) NOT NULL,
  `accountId` int(11) NOT NULL,
  `expireAt` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `auth_code`;
CREATE TABLE `auth_code` (
  `id` varchar(33) NOT NULL,
  `accountId` int(11) NOT NULL,
  `expireAt` bigint(20) NOT NULL,
  `type` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;