DROP TABLE IF EXISTS `presto`.`b_continue_order`;
CREATE TABLE `presto`.`b_continue_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  `order_id` BIGINT(20) NOT NULL COMMENT '',
  `continue_amount` DECIMAL NOT NULL COMMENT '续借金额总价',
  `continue_days` int not null comment '续借天数',
  `deleted_flag` INT(1) NOT NULL DEFAULT 0 COMMENT '',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '续惜阅';