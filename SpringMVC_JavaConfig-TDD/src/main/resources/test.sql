/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-01-23 08:27:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1516647759184', 'Syed Hasan', 'mainuls18@gmail.com', '24', 'Male', 'USA', '+8801511333221');
INSERT INTO `tbl_user` VALUES ('1516673501643', 'Syed Hasan', 'mainuls19@gmail.com', '24', 'Male', 'Bangladesh', '+8801511333221');

-- ----------------------------
-- Procedure structure for `addRole`
-- ----------------------------
DROP PROCEDURE IF EXISTS `addRole`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addRole`(IN `r_id` varchar(255),IN `r_name` varchar(255))
BEGIN
	#Routine body goes here...
	INSERT INTO roles(roles.role_id, roles.role_name)VALUES(r_id,r_name);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `getAllRoles`
-- ----------------------------
DROP PROCEDURE IF EXISTS `getAllRoles`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRoles`()
SELECT * FROM roles
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `getAllUsers`
-- ----------------------------
DROP PROCEDURE IF EXISTS `getAllUsers`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUsers`()
BEGIN
	#Routine body goes here...
	SELECT * FROM userdetails;
END
;;
DELIMITER ;
