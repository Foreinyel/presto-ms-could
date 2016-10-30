CREATE TABLE `presto`.`u_invite_code` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `code` VARCHAR(45) NOT NULL COMMENT '',
  `status` INT(1) NOT NULL DEFAULT 0 COMMENT '0-正常；1-废弃',
  `deleted_flag` int(1) NOT NULL DEFAULT '0',
  `created_date` DATETIME NULL COMMENT '',
  `created_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `idx_invite_code_id` (`id` ASC) ,
  UNIQUE INDEX `idx_invite_code_code` (`code` ASC))
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '邀请码'
