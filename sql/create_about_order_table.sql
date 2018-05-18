DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(50) NOT NULL,
  `content` varchar(128) NOT NULL COMMENT '订单内容',
  `status` int(5) NOT NULL DEFAULT '0' COMMENT '订单状态: 0:未支付 1:支付待确认 2:支付完成 3:已取消',
  `payment` varchar(50) NULL COMMENT '订单金额,精确到两位小数',
  `accountId` int(11) NOT NULL COMMENT '用户id',
  `createdAt` datetime NOT NULL,
  `payedAt` datetime NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(50) NOT NULL COMMENT '订单id',
  `certiId` int(11) NOT NULL COMMENT '证书id',
  `eName` varchar(50) NOT NULL COMMENT '报考人姓名',
  `eGender` varchar(16) NOT NULL COMMENT '报考人性别',
  `eAge` varchar(8) NOT NULL COMMENT '报考人年龄',
  `eIdNumber` varchar(24) NOT NULL COMMENT '报考人身份证号',
  `ePhone` varchar(24) NOT NULL COMMENT '报考人手机号',
  `eQQ` varchar(24) NOT NULL COMMENT '报考人QQ号',
  `eAddr` varchar(256) NOT NULL COMMENT '报考人联系地址',
  `eKzLevel` varchar(24) NULL COMMENT '报考人的报考级别,幼儿园,小学...',
  `eKzCourse` varchar(24) NULL COMMENT '报考人的报考课程,语文,数学...',
  `eKzType` varchar(24) NOT NULL COMMENT '报考人的报考类型,笔试,面试',
  `eKzProject` varchar(24) NOT NULL COMMENT '报考人的报考项目,培训,考试',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;