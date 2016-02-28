ALTER TABLE `myplace_db`.`feedback_info` ADD COLUMN `name` VARCHAR(45) AFTER `email`,
 ADD COLUMN `phone` VARCHAR(15) AFTER `name`;

update business_category set status=2  where category_name='Adult';

ALTER TABLE `myplace_db`.`business_report_info` MODIFY COLUMN `business_id` BIGINT(20) UNSIGNED,
 ADD COLUMN `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1-business,2-user' AFTER `status`;


 ALTER TABLE `myplace_db`.`business_info` CHANGE COLUMN `business_desc` `jd_business_desc` VARCHAR(5000) CHARACTER SET latin1 COLLATE latin1_swedish_ci,
 ADD COLUMN `business_desc` VARCHAR(500) AFTER `business_view`;


