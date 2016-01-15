CREATE TABLE `app_stats` (
  `user_id` bigint(20) unsigned NOT NULL,
  `stats` longtext NOT NULL,
  `stats_date` datetime NOT NULL,
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1-active,2-inactive',
  KEY `Index_userid` (`user_id`),
  KEY `Index_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `notification_info` (
  `notification_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1-Business create,2-event type',
  `description` varchar(150) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `imgurl` varchar(155) DEFAULT NULL,
  `clkurl` varchar(155) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1-active,2-inactive,3-deleted',
  `create_time` datetime NOT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `Ind_userid` (`user_id`),
  KEY `ind_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;