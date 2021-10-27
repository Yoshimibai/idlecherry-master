/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : idlecherry

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-10-14 20:37:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `commID` int NOT NULL,
  `commName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `commDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updateTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `commStatus` int DEFAULT NULL,
  `categoryID` int DEFAULT NULL,
  `userID` int DEFAULT NULL,
  PRIMARY KEY (`commID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', 'cup', 'very good', '100.00', '...', '121212', '1', '2', '4');
INSERT INTO `commodity` VALUES ('2', 'pen', 'not very good', '10.00', '...', '121212', '1', '2', '4');
INSERT INTO `commodity` VALUES ('3', 'laptop', 'Apple!', '15000.00', '...', '121212', '0', '2', '4');
INSERT INTO `commodity` VALUES ('4', 'iphone', 'iPhone 13 pro max', '8000.00', '...', '111111', '1', '3', '3');

-- ----------------------------
-- Table structure for commodity_category
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category`;
CREATE TABLE `commodity_category` (
  `categoryID` int NOT NULL,
  `categoryName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of commodity_category
-- ----------------------------
INSERT INTO `commodity_category` VALUES ('1', 'Clothes');
INSERT INTO `commodity_category` VALUES ('2', 'Books');
INSERT INTO `commodity_category` VALUES ('3', 'Home Applicance');
INSERT INTO `commodity_category` VALUES ('4', 'Electronics');
INSERT INTO `commodity_category` VALUES ('5', 'Sports');
INSERT INTO `commodity_category` VALUES ('6', 'Health & Beauty');
INSERT INTO `commodity_category` VALUES ('7', 'Entertainment');
INSERT INTO `commodity_category` VALUES ('8', 'Others');

-- ----------------------------
-- Table structure for commodity_comment
-- ----------------------------
DROP TABLE IF EXISTS `commodity_comment`;
CREATE TABLE `commodity_comment` (
  `commentID` int NOT NULL,
  `commID` int DEFAULT NULL,
  `userID` int DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `commentTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`commentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of commodity_comment
-- ----------------------------
INSERT INTO `commodity_comment` VALUES ('1', '1', '1', 'hahaha!!!', '去年');
INSERT INTO `commodity_comment` VALUES ('2', '1', '3', 'no！', '今年');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeID` int NOT NULL,
  `noticeTypeID` int DEFAULT NULL,
  `userID` int DEFAULT NULL,
  `noticeTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `noticeContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`noticeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for notice_type
-- ----------------------------
DROP TABLE IF EXISTS `notice_type`;
CREATE TABLE `notice_type` (
  `noticeTypeID` int NOT NULL,
  `noticeTypeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`noticeTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of notice_type
-- ----------------------------
INSERT INTO `notice_type` VALUES ('1', 'Commodity Comment');
INSERT INTO `notice_type` VALUES ('2', 'Commodity Update');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lastName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `faculty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `startYear` int DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Jiapei', 'Tian', 'tianjiapei', '12121212121', 'tianjiapei@haha.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('2', 'Fusong', 'Shi', 'a5878592b', '130133333333', 'Stoney0616@126.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3', 'Stoooooooooooooney ', 'Shi', '123', '1234567899', 'haha@123.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('4', 'xiaoming', 'wang', 'hahaha', '234423423dfdf42ffef', 'stoney666@126.com', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection` (
  `collectionID` int NOT NULL,
  `commID` int DEFAULT NULL,
  `userID` int DEFAULT NULL,
  `commName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `commDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `collectionTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`collectionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user_collection
-- ----------------------------
INSERT INTO `user_collection` VALUES ('1', '1', '4', 'cup', 'very good', '100.00', '2021-10-11');
INSERT INTO `user_collection` VALUES ('2', '2', '4', 'pen', 'not Very good', '10.00', '2021-10-11');
