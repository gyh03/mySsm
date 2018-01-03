/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : myssm

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-12-24 15:07:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orgi_name` varchar(255) DEFAULT NULL COMMENT '文件原始名',
  `file_name` varchar(255) DEFAULT NULL COMMENT '生成的文件名',
  `file_real_path` varchar(500) DEFAULT NULL COMMENT '文件物理路径',
  `file_relt_path` varchar(500) DEFAULT NULL COMMENT '文件相对路径',
  `url` varchar(500) DEFAULT NULL COMMENT '访问url',
  `createtime` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_ope_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_ope_logs`;
CREATE TABLE `t_ope_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ope_type` varchar(255) DEFAULT NULL COMMENT '操纵类型',
  `node` varchar(255) DEFAULT NULL COMMENT '操作对象',
  `result_flag` tinyint(1) DEFAULT NULL COMMENT '操作结果：0失败，1成功',
  `param_json` varchar(1000) DEFAULT NULL COMMENT '参数',
  `result_json` varchar(1000) DEFAULT NULL COMMENT '结果',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '用户ip',
  `login_browser` varchar(255) DEFAULT NULL COMMENT '用户浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别：0男1女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `salary` decimal(10,2) DEFAULT NULL COMMENT '薪水',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `delfalg` tinyint(1) DEFAULT '0' COMMENT '是否删除，0否，1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;
