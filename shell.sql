/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : shell

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 22/02/2021 18:43:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for environment_info
-- ----------------------------
DROP TABLE IF EXISTS `environment_info`;
CREATE TABLE `environment_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `environment_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classes_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `js_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of environment_info
-- ----------------------------
INSERT INTO `environment_info` VALUES (1, '厦大', 'prd', '/export/tomcat/webapps', '/export/tomcat/statics', '2021-02-19 15:16:47.463000', NULL);
INSERT INTO `environment_info` VALUES (2, '厦大', 'dev', '/export/tomcat/webapps/dev', '/export/tomcat/statics/dev', '2021-02-19 15:16:47.463000', NULL);
INSERT INTO `environment_info` VALUES (3, '厦大', 'uat', '/export/tomcat/webapps', '/export/tomcat/statics', '2021-02-19 15:45:09.214000', NULL);
INSERT INTO `environment_info` VALUES (4, '哈工大', 'uat', '/export/tomcat/webapps', '/export/tomcat/statics', '2021-02-19 15:45:31.489000', NULL);
INSERT INTO `environment_info` VALUES (5, '哈工大', 'uat', '/export/tomcat/webapps', '/export/tomcat/statics', '2021-02-19 16:13:30.025000', NULL);
INSERT INTO `environment_info` VALUES (6, '哈工大', 'uat', '/export/tomcat/webapps', '/export/tomcat/statics', '2021-02-19 16:45:33.610000', NULL);

-- ----------------------------
-- Table structure for jenkins_info
-- ----------------------------
DROP TABLE IF EXISTS `jenkins_info`;
CREATE TABLE `jenkins_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `environment_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classes_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `statics_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jenkins_info
-- ----------------------------
INSERT INTO `jenkins_info` VALUES (1, '厦大', 'dev', '/var/jenkins_home/workspace/厦门大学/厦门大学-教务-dev/target/university/WEB-INF/classes', '/var/jenkins_home/workspace/厦门大学/厦门大学-教务-dev/target/university/statics', '2021-02-22 10:01:37.000000', NULL);

SET FOREIGN_KEY_CHECKS = 1;
