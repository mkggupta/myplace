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
