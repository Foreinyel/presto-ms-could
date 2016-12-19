CREATE TABLE `presto`.`u_user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `mobile` VARCHAR(45) NOT NULL COMMENT '手机号码',
  `invite_code_id` BIGINT(20) NULL COMMENT '注册邀请码',
  `passwd` VARCHAR(45) NULL COMMENT '登录密码(验证码)',
  `deleted_flag` int(1) NOT NULL DEFAULT '0',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `idx_user_id` (`id` ASC) ,
  UNIQUE INDEX `idx_user_mobile` (`mobile` ASC) )
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '用户表';
	
ALTER TABLE `presto`.`u_user`
  ADD COLUMN `name` VARCHAR(200) NULL COMMENT '用户昵称' AFTER `mobile`;