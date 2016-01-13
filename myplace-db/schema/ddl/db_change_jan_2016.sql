ALTER TABLE `myplace_db`.`feedback_info` MODIFY COLUMN `status` TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1-Open,2-Reviewed,3-closed,4-can be done,5- can not be done,6-delete';

ALTER TABLE `myplace_db`.`feedback_reply_info` ADD COLUMN `status` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '1-Open,2-Reviewed,3-closed,4-can be done,5- can not be done,6-delete' AFTER `replying_user_id`;

CREATE TABLE `user_push_info` (
  `user_id` bigint(20) unsigned NOT NULL,
  `device_id` varchar(255) NOT NULL,
  `push_key` varchar(255) NOT NULL,
  `platform` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1-android,2-iPhone',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0-inActive,1-active',
  `create_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `myplace_db`.`business_file_info` MODIFY COLUMN `file_location` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;


CREATE TABLE `profile_file_info` (
  `user_id` bigint(10) unsigned NOT NULL,
  `file_id` varchar(128) NOT NULL,
  `status` enum('ACTIVE','INACTIVE','DELETED') NOT NULL DEFAULT 'ACTIVE',
  `media_type` varchar(10) NOT NULL,
  `ext` varchar(10) DEFAULT NULL,
  `size` int(10) unsigned DEFAULT '0',
  `file_location` varchar(255) NOT NULL,
  `file_name` varchar(145) NOT NULL,
  KEY `Index_bId` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `default_file_info` (
  `id` int(10) unsigned NOT NULL COMMENT 'this will have category id or subcategoryid etc',
  `file_id` varchar(128) NOT NULL,
  `status` enum('ACTIVE','INACTIVE','DELETED') NOT NULL DEFAULT 'ACTIVE',
  `media_type` varchar(10) NOT NULL,
  `ext` varchar(10) DEFAULT NULL,
  `size` int(10) unsigned DEFAULT '0',
  `file_location` varchar(255) NOT NULL,
  `file_name` varchar(145) NOT NULL,
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '0-unknown profile pic 1- male image,2-female image,3-category, 4-subcategory',
  KEY `Index_Id` (`type`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `myplace_db`.`default_file_info` MODIFY COLUMN `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-unknown profile pic 1- male image,2-female image,3-category, 4-subcategory';


