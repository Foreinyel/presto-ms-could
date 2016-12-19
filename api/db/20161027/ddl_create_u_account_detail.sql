DROP TABLE IF EXISTS `presto`.`u_account_detail`;
CREATE TABLE `presto`.`u_account_detail` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `account_id` BIGINT(20) NOT NULL COMMENT '',
  `source_type` INT(3) NOT NULL COMMENT '明细来源:0-微信充值;1-订单支付',
  `source_type_id` bigint(20) null comment '明细来源id',
  `amount` DECIMAL(20,4) not null comment '金额',
  `deleted_flag` INT(1) NOT NULL DEFAULT 0 COMMENT '',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_account_detail_id` (`id` ASC))
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '用户余额账户明细';