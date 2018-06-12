CREATE DATABASE IF NOT EXISTS robot CHARACTER SET UTF8;
USE robot;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
	ID INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	USERNAME VARCHAR(64) NOT NULL DEFAULT '',
	PASSWORD VARCHAR(32) NOT NULL DEFAULT '',
	PRIMARY KEY(ID),
	KEY I_SYS_USER_USERNAME (USERNAME)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户表';

-- ----------------------------
-- Table structure for inspection_data
-- ----------------------------
DROP TABLE IF EXISTS `inspection_data`;
CREATE TABLE `inspection_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `HighT` double(8,2) DEFAULT NULL COMMENT '高区湿度摄氏度',
  `HighH` double(8,2) DEFAULT NULL COMMENT '高区湿度百分比',
  `MiddleT` double(8,2) DEFAULT NULL COMMENT '中区湿度摄氏度',
  `MiddleH` double(8,2) DEFAULT NULL COMMENT '中区湿度百分比',
  `LowT` double(8,2) DEFAULT NULL COMMENT '低区湿度摄氏度',
  `LowH` double(8,2) DEFAULT NULL COMMENT '低区湿度百分比',
  `ArmT` double(8,2) DEFAULT NULL COMMENT '线缆检测温度摄氏度',
  `WindSpeed` double(11,2) DEFAULT NULL COMMENT '风速米每秒',
  `RobotX` double(11,2) DEFAULT NULL COMMENT '机器人坐标X(毫米)',
  `RobotY` double(11,2) DEFAULT NULL COMMENT '机器人坐标Y',
  `RobotH` double(11,2) DEFAULT NULL COMMENT '机器人朝向角TH(角度)',
  `Battery` double(8,2) DEFAULT NULL COMMENT '机器人电量',
  `Target` varchar(512) DEFAULT NULL COMMENT '导航点',
  `CreateTime` datetime DEFAULT NULL COMMENT '写入数据时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
