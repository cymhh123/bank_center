/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.32 : Database - bk_center
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bk_center` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bk_center`;

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 DEFAULT '1' COMMENT '日志类型(1：接入日志；2：错误日志)',
  `title` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '日志标题',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COMMENT '操作提交的数据',
  `exception` text CHARACTER SET utf8 COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='日志表';

/*Table structure for table `yh_admin` */

DROP TABLE IF EXISTS `yh_admin`;

CREATE TABLE `yh_admin` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `account` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `yh_auth_history` */

DROP TABLE IF EXISTS `yh_auth_history`;

CREATE TABLE `yh_auth_history` (
  `id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `persion` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `card_no` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `point_name` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `device_no` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `tip_status` int(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  `img_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_esperanto_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `yh_escort` */

DROP TABLE IF EXISTS `yh_escort`;

CREATE TABLE `yh_escort` (
  `id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `name` varchar(64) CHARACTER SET latin1 DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `card_no` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `img_url` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `bank_id` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  `yy_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `yh_point` */

DROP TABLE IF EXISTS `yh_point`;

CREATE TABLE `yh_point` (
  `id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `name` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `address` varchar(300) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `persion` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `device_no` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `yy_admin` */

DROP TABLE IF EXISTS `yy_admin`;

CREATE TABLE `yy_admin` (
  `id` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(64) CHARACTER SET latin1 DEFAULT NULL,
  `bank_id` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `account` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `yy_bank_info` */

DROP TABLE IF EXISTS `yy_bank_info`;

CREATE TABLE `yy_bank_info` (
  `id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `name` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `persion` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `yy_escort` */

DROP TABLE IF EXISTS `yy_escort`;

CREATE TABLE `yy_escort` (
  `id` varchar(32) CHARACTER SET latin1 NOT NULL,
  `name` varchar(64) CHARACTER SET latin1 DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `card_no` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `img_url` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `bank_id` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_status` int(2) DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
