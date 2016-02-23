ALTER TABLE `myplace_db`.`feedback_info` ADD COLUMN `name` VARCHAR(45) AFTER `email`,
 ADD COLUMN `phone` VARCHAR(15) AFTER `name`;

update business_category set status=2  where category_name='Adult';
