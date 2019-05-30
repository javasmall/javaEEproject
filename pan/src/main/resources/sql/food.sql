/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : biggsai.com:3306
 Source Schema         : food

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 31/05/2019 07:10:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('niannian', '123456');

-- ----------------------------
-- Table structure for path
-- ----------------------------
DROP TABLE IF EXISTS `path`;
CREATE TABLE `path`  (
  `sapid` int(10) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of path
-- ----------------------------
INSERT INTO `path` VALUES (11111, '/food/11111/营业执照-张爱民.pdf', '营业执照-张爱民.pdf', '2019-03-03 14:31', NULL);
INSERT INTO `path` VALUES (11111, '/food/11111/test.html', 'test.html', '2019-03-06 19:23', NULL);
INSERT INTO `path` VALUES (11111, '/food/11111/果核2.0本创立项申报书.docx', '果核2.0本创立项申报书.docx', '2019-03-06 19:32', NULL);
INSERT INTO `path` VALUES (11111, '/food/11111/33.jpg', '33.jpg', '2019-03-06 19:32', NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `sapid` int(10) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (66, '688', '66666gsd');
INSERT INTO `users` VALUES (11111, '222', 'bigsai22');
INSERT INTO `users` VALUES (7777, '7777', '7777');
INSERT INTO `users` VALUES (515, 'gd', 'dgag');

SET FOREIGN_KEY_CHECKS = 1;
