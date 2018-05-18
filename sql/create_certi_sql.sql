DROP TABLE IF EXISTS `certi`;
CREATE TABLE `certi` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(64) NOT NULL COMMENT '证书名称',
	`desc` varchar(512) NOT NULL COMMENT '证书描述',
	`pId` int(11) DEFAULT '-1' COMMENT '父证书的id',
	`status` int(11) DEFAULT '1' COMMENT '证书状态,0:未激活,1:激活',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
INSERT INTO `certi` (`name`,`desc`) VALUES ('教师资格证','教师资格证是教育行业从业教师的许可证。在我国，师范类大学毕业生须在学期期末考试中通过学校开设的教育学和教育心理学课程考试');
*/

/*
     教室资格证
*/
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('2','教室资格证','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('3','导游资格证','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('4','初级会计师','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('5','普通话','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('6','英语四级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('7','企业人力资源管理师','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('8','人力资源管理师三级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('9','秘书证','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('10','秘书证二级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('11','秘书证三级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('12','秘书证四级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('13','公务员培训','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('14','驾驶员资格证','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('15','计算机等级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('16','银行从业资格','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('17','证券从业资格','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('18','人力资源管理师二级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('19','人力资源管理师四级','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('20','会计真账实操','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');
INSERT INTO `certi` (`id`,`name`,`desc`) VALUES ('21','幼儿园园长证','导游员资格证书，是标志某人具备从事导游职业资格的证书，证明了某人具备导游职业的资格。');


DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`kzType` int(11) NOT NULL DEFAULT '0' COMMENT '考证类型分为:1:笔试,2:面试,0:默认',
	`exam` float(7,2) NULL DEFAULT '0' COMMENT '考试费',
	`train` float(7,2) NULL DEFAULT '0' COMMENT '培训费',
	`material` float(7,2) NULL DEFAULT '0' COMMENT '资料费',
	`certiId` int(11) NOT NULL COMMENT '证书id',
	`merge` float(7,2) NULL DEFAULT '0' COMMENT '总收费,不区分考试费和培训费,默认为0',
	`trainType` int(11) NOT NULL DEFAULT '0' COMMENT '培训班类型:0:默认 1:全程班 2:冲刺班 3:定制班',
	`descri` varchar(64) NULL COMMENT '费用描述信息',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `price` (`certiId`,`exam`,`train`,`material`) VALUES ('3','280','700','180');
INSERT INTO `price` (`certiId`,`exam`,`train`) VALUES ('4','150','650');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`) VALUES ('5','180','200','20');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`) VALUES ('6','28','680','80');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`) VALUES ('8','370','450','80','1');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`,`descri`) VALUES ('8','370','150','80','2','冲刺班无优惠');
INSERT INTO `price` (`certiId`,`exam`) VALUES ('18','700');
INSERT INTO `price` (`certiId`,`exam`) VALUES ('19','300');
INSERT INTO `price` (`certiId`,`exam`,`material`) VALUES ('10','700','200');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`) VALUES ('11','350','250','80','2');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`) VALUES ('12','280','250','80','2');
INSERT INTO `price` (`certiId`,`exam`,`train`,`descri`) VALUES ('13','100','2980','零售资料费7.5折');
INSERT INTO `price` (`certiId`,`exam`,`train`) VALUES ('14','1450','2600');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`,`descri`) VALUES ('15','100','480','60','1','');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`,`descri`) VALUES ('15','100','300','60','2','无优惠');
INSERT INTO `price` (`certiId`,`exam`,`train`,`material`,`trainType`,`descri`) VALUES ('15','100','480','60','3','6人以内小班');
INSERT INTO `price` (`certiId`,`merge`,`descri`) VALUES ('16','200','100元/科,资料费7折');
INSERT INTO `price` (`certiId`,`merge`,`descri`) VALUES ('17','180','90元/科,资料费7折');
INSERT INTO `price` (`certiId`,`merge`,`descri`) VALUES ('20','1880','培训费');
INSERT INTO `price` (`certiId`,`merge`,`descri`) VALUES ('21','3850','总费用');
INSERT INTO `price` (`certiId`,`kzType`,`trainType`,`exam`,`train`,`material`,`descri`) VALUES ('2','1','1','90','650','130','只培训公共课程,专业课大部分可送电子版.笔试90元/科');
INSERT INTO `price` (`certiId`,`kzType`,`trainType`,`exam`,`train`,`material`,`descri`) VALUES ('2','1','2','90','380','130','只培训公共课程,专业课大部分可送电子版.笔试90元/科');
INSERT INTO `price` (`certiId`,`kzType`,`trainType`,`exam`,`train`,`material`,`descri`) VALUES ('2','2','0','310','580','80','只培训公共课程,专业课大部分可送电子版.');
/*
	教师资格证的面试部分费用未添加完全
*/
