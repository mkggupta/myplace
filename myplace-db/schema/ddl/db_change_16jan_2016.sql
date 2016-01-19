CREATE TABLE `forget_password_verification` (
  `user_id` bigint(20) unsigned NOT NULL,
  `verification_code` varchar(200) NOT NULL,
  `created_date` datetime NOT NULL,
  `expiry_date` datetime default NULL,
  `status` tinyint(1) NOT NULL default '0' COMMENT '0-unused\n,1-used,2-expired',
  `id` bigint(20) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_email_verification` (
  `user_id` bigint(20) unsigned NOT NULL,
  `email_id` varchar(100) NOT NULL,
  `verification_code` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL default '0' COMMENT '0-active.\n1-verified,2-expired',
  `created_date` datetime NOT NULL,
  `expiry_date` datetime default NULL,
  `id` bigint(20) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;