/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : onforyou

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2015-10-24 16:26:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '电子邮件',
  `mobile_phone` varchar(15) NOT NULL COMMENT '手机号码',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `create_time` bigint(16) NOT NULL COMMENT '创建时间',
  `is_verify_phone` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已验证手机号码',
  `apply_verify_phone_time` bigint(16) DEFAULT NULL COMMENT '申请验证手机号码时间',
  `head_image` varchar(255) DEFAULT '' COMMENT '头像',
  `verify_token` varchar(255) NOT NULL COMMENT '验证token',
  `is_verify_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否验证邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_login_name` (`login_name`) USING BTREE,
  UNIQUE KEY `uk_user_email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
