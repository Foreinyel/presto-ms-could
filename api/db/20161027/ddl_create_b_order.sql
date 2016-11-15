CREATE TABLE `presto`.`b_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  `status` INT NOT NULL COMMENT '订单状态:0-新建;10-待支付;20-待发货;30-配送中;40-惜阅中;50-待归还;',
  `amount` DECIMAL NOT NULL COMMENT '订单总价',
  `date_from` DATETIME NOT NULL COMMENT '借阅日期从',
  `date_end` DATETIME NOT NULL COMMENT '借阅日期到',
  `order_date` DATETIME NOT NULL COMMENT '下单日期',
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
	COMMENT = '订单头表'