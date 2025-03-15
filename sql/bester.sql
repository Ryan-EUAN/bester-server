/*
 Navicat Premium Data Transfer

 Source Server         : 本地SQL
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : bester

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 15/03/2025 13:25:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_comment`;
CREATE TABLE `tb_blog_comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `send_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `blog_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `comment` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `send_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '跟博客评论相关的' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_nav_bar_second_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_nav_bar_second_info`;
CREATE TABLE `tb_nav_bar_second_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nav_id` int NULL DEFAULT NULL COMMENT '主导航栏ID',
  `nav_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏名称',
  `nav_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏路径',
  `nav_sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏排序',
  `nav_disable` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏禁用情况(0禁用,1使用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_nav_bar_second_info
-- ----------------------------
INSERT INTO `tb_nav_bar_second_info` VALUES (1, 4, '每日签到', 'qd', '0', '1');
INSERT INTO `tb_nav_bar_second_info` VALUES (2, 4, '排行榜', 'ph', '0', '1');

-- ----------------------------
-- Table structure for tb_navigation_bar_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_navigation_bar_info`;
CREATE TABLE `tb_navigation_bar_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `nav_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏名称',
  `nav_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导航栏路径',
  `nav_sort` int NULL DEFAULT NULL COMMENT '导航栏排序',
  `nav_disable` bit(1) NULL DEFAULT NULL COMMENT '导航栏禁用情况(0禁用,1使用)',
  `nav_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '导航栏信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_navigation_bar_info
-- ----------------------------
INSERT INTO `tb_navigation_bar_info` VALUES (1, '首页', 'home', 1, b'1', 'HomeOutlined');
INSERT INTO `tb_navigation_bar_info` VALUES (2, '老板找技术', 'zjs', 3, b'0', '');
INSERT INTO `tb_navigation_bar_info` VALUES (3, '站点功能', 'zd', 2, b'1', '');
INSERT INTO `tb_navigation_bar_info` VALUES (4, '快捷导航', 'kj', 4, b'1', '');

-- ----------------------------
-- Table structure for tb_rotation_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_rotation_map`;
CREATE TABLE `tb_rotation_map`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '跳转地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_rotation_map
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `is_admin` bit(1) NOT NULL DEFAULT (0) COMMENT '是否是管理员(0不是,1是)',
  `is_online` bit(1) NOT NULL COMMENT '是否在线(0不在线,1在线)',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '绑定的邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户登录鉴权用的表，存储账号密码的' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '202cb962ac59075b964b07152d234b70', b'0', b'0', NULL);
INSERT INTO `tb_user` VALUES (3, NULL, NULL, b'0', b'0', '1134497885@qq.com');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `followers` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `following` int NULL DEFAULT NULL,
  `posts` int NULL DEFAULT NULL COMMENT '贴吧',
  `gold_coin` bigint NULL DEFAULT NULL COMMENT '金币',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息存储表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES (1, 1, 'Admin', 'https://img1.baidu.com/it/u=728383910,3448060628&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=800', '0', 0, 0, 100000000);
INSERT INTO `tb_user_info` VALUES (3, 3, '用户很懒，还未取名字', NULL, '0', 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
