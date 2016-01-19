-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.50-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema my_order
--

CREATE DATABASE IF NOT EXISTS my_order;
USE my_order;

--
-- Definition of table `city_info`
--
CREATE TABLE `city_info` (
  `city_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `district_id` int(10) unsigned NOT NULL,
  `city_name` varchar(45) NOT NULL,
  `pincode` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  KEY `Index_city` (`city_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city_info`
--

/*!40000 ALTER TABLE `city_info` DISABLE KEYS */;
INSERT INTO `city_info` (`city_id`,`district_id`,`city_name`,`pincode`) VALUES 
 (1,1,'saharanpur',NULL),
 (2,1,'kankarkui',NULL);
/*!40000 ALTER TABLE `city_info` ENABLE KEYS */;


--
-- Definition of table `country_info`
--
CREATE TABLE `country_info` (
  `country_code` char(3) NOT NULL,
  `country_name` varchar(50) NOT NULL,
  PRIMARY KEY (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country_info`
--

/*!40000 ALTER TABLE `country_info` DISABLE KEYS */;
INSERT INTO `country_info` (`country_code`,`country_name`) VALUES 
 ('IND','INDIA');
/*!40000 ALTER TABLE `country_info` ENABLE KEYS */;


--
-- Definition of table `food_category`
--
CREATE TABLE `food_category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food_category`
--

/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` (`category_id`,`category_name`) VALUES 
 (1,'North Indian'),
 (2,'South Indian'),
 (3,'Chinese'),
 (4,'Fast Food'),
 (5,'Desserts');
/*!40000 ALTER TABLE `food_category` ENABLE KEYS */;


--
-- Definition of table `food_item_name`
--
CREATE TABLE `food_item_name` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `item_name` varchar(45) NOT NULL,
  `item_type` enum('VEG','NON VEG') NOT NULL DEFAULT 'VEG',
  `item_description` varchar(150) DEFAULT NULL,
  `item_status` enum('ACT','INACT') NOT NULL DEFAULT 'ACT',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food_item_name`
--

/*!40000 ALTER TABLE `food_item_name` DISABLE KEYS */;
INSERT INTO `food_item_name` (`item_id`,`category_id`,`item_name`,`item_type`,`item_description`,`item_status`) VALUES 
 (1,2,'masala dosa','VEG','Its made from rava after putting some patato','ACT');
/*!40000 ALTER TABLE `food_item_name` ENABLE KEYS */;


--
-- Definition of table `ingredient_cost`
--
CREATE TABLE `ingredient_cost` (
  `ingredient_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ingredient_name` varchar(45) NOT NULL,
  `ingredient_cost` double NOT NULL,
  `ingredient_quantity` double NOT NULL,
  `ingredient_measure` enum('kg','lt') NOT NULL DEFAULT 'kg',
  `ingredient_state` char(5) NOT NULL,
  `ingredient_status` enum('ACT','INACT') NOT NULL DEFAULT 'ACT',
  `creation_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `currency` enum('INR','DOLLAR') NOT NULL DEFAULT 'INR',
  `local_name` varchar(45) DEFAULT NULL,
  `loose_available` enum('Y','N') NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`ingredient_id`),
  KEY `Index_name` (`ingredient_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingredient_cost`
--

/*!40000 ALTER TABLE `ingredient_cost` DISABLE KEYS */;
INSERT INTO `ingredient_cost` (`ingredient_id`,`ingredient_name`,`ingredient_cost`,`ingredient_quantity`,`ingredient_measure`,`ingredient_state`,`ingredient_status`,`creation_date`,`modified_date`,`currency`,`local_name`,`loose_available`) VALUES 
 (1,'patato',20,1,'kg','DL','ACT',NULL,NULL,'INR','Aaloo','Y');
/*!40000 ALTER TABLE `ingredient_cost` ENABLE KEYS */;


--
-- Definition of table `item_ingredient`
--
CREATE TABLE `item_ingredient` (
  `ingredent_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ingredient_name` varchar(45) NOT NULL,
  `ingredient_quantity` double NOT NULL,
  `ingredient_measure` enum('gm','ml') NOT NULL DEFAULT 'gm',
  `item_id` int(10) unsigned NOT NULL,
  `ingredient_mandatory` enum('Y','N') NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`ingredent_id`),
  KEY `Index_item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_ingredient`
--

/*!40000 ALTER TABLE `item_ingredient` DISABLE KEYS */;
INSERT INTO `item_ingredient` (`ingredent_id`,`ingredient_name`,`ingredient_quantity`,`ingredient_measure`,`item_id`,`ingredient_mandatory`) VALUES 
 (1,'patato',100,'gm',1,'Y'),
 (2,'oil',50,'ml',1,'Y'),
 (3,'rava',50,'gm',1,'Y'),
 (4,'salt',5,'gm',1,'Y'),
 (5,'red chilli',5,'gm',1,'Y'),
 (6,'termeric',5,'gm',1,'Y'),
 (7,'green chilli',5,'gm',1,'Y'),
 (8,'onion',50,'gm',1,'N');
/*!40000 ALTER TABLE `item_ingredient` ENABLE KEYS */;


--
-- Definition of table `list_ingredient_cost`
--
CREATE TABLE `list_ingredient_cost` (
  `ingredient_id` int(10) unsigned NOT NULL,
  `ingredient_name` varchar(45) NOT NULL,
  `ingredient_cost` double NOT NULL,
  `ingredient_measure` enum('gm','ml') NOT NULL DEFAULT 'gm',
  `ingredient_state` char(5) NOT NULL,
  `ingredient_status` enum('ACT','INACT') NOT NULL DEFAULT 'ACT',
  `creation_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `currency` enum('paise') NOT NULL DEFAULT 'paise',
  PRIMARY KEY (`ingredient_id`),
  KEY `Index_name` (`ingredient_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `list_ingredient_cost`
--

/*!40000 ALTER TABLE `list_ingredient_cost` DISABLE KEYS */;
INSERT INTO `list_ingredient_cost` (`ingredient_id`,`ingredient_name`,`ingredient_cost`,`ingredient_measure`,`ingredient_state`,`ingredient_status`,`creation_date`,`modified_date`,`currency`) VALUES 
 (1,'patato',2,'gm','DL','ACT',NULL,NULL,'paise');
/*!40000 ALTER TABLE `list_ingredient_cost` ENABLE KEYS */;


--
-- Definition of table `state_district_info`
--
CREATE TABLE `state_district_info` (
  `country_code` char(3) NOT NULL,
  `state_name` varchar(20) NOT NULL,
  `district_name` varchar(25) NOT NULL,
  `state_code` char(3) NOT NULL,
  `district_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`district_id`),
  KEY `Index_state_name` (`state_name`),
  KEY `Indx_cntry` (`country_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state_district_info`
--

/*!40000 ALTER TABLE `state_district_info` DISABLE KEYS */;
INSERT INTO `state_district_info` (`country_code`,`state_name`,`district_name`,`state_code`,`district_id`) VALUES 
 ('IND','DELHI','DELHI','DL',1);
/*!40000 ALTER TABLE `state_district_info` ENABLE KEYS */;


--
-- Definition of table `user_auth`
--
CREATE TABLE `user_auth` (
  `auth_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-unvarified,1-active,2-inactive,3-blocked',
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `last_login_time` datetime NOT NULL,
  `registration_mode` tinyint(2) NOT NULL COMMENT '1-facebook,2-twitter,3-google,4-web,5-app',
  `last_login_mode` tinyint(2) NOT NULL COMMENT '1-facebook,2-twitter,3-google,4-web,5-app',
  `login_status` tinyint(1) DEFAULT '0' COMMENT '0-logout,1-loggedin,2-idle',
  `current_client_version` varchar(100) DEFAULT NULL,
  `current_platform` varchar(45) DEFAULT NULL,
  `latitude` decimal(20,16) DEFAULT '0.0000000000000000',
  `longitude` decimal(20,16) DEFAULT '0.0000000000000000',
  `last_location` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`auth_id`),
  UNIQUE KEY `username_UNIQUE` (`username`,`registration_mode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_auth`
--

/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;


--
-- Definition of table `user_info`
--
CREATE TABLE `user_info` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `business_name` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0-unknown,1-male,2-female',
  `primary_email_address` varchar(100) DEFAULT NULL,
  `primary_contact_number` varchar(20) DEFAULT NULL,
  `alternate_contact_number` varchar(45) DEFAULT NULL,
  `contact_address1` varchar(45) DEFAULT NULL,
  `contact_address2` varchar(45) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country` varchar(100) DEFAULT 'United States',
  `modified_date` datetime DEFAULT NULL,
  `language` varchar(100) DEFAULT 'English',
  `location` varchar(150) DEFAULT NULL,
  `user_desc` varchar(500) DEFAULT NULL,
  `web_site` varchar(150) DEFAULT NULL,
  `secondary_email_address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_username` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


--
-- Definition of table `user_third_party_auth`
--
CREATE TABLE `user_third_party_auth` (
  `user_id` bigint(10) unsigned NOT NULL,
  `third_party_id` tinyint(2) NOT NULL,
  `user_key` varchar(100) NOT NULL,
  `app_key` varchar(100) NOT NULL,
  UNIQUE KEY `user_third_party_auth_uq` (`user_id`,`third_party_id`) USING BTREE,
  KEY `Index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_third_party_auth`
--

/*!40000 ALTER TABLE `user_third_party_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_third_party_auth` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
