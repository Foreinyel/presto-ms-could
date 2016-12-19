DROP TABLE IF EXISTS `presto`.`b_return_book`;
CREATE TABLE `presto`.`b_return_book` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  `order_id` BIGINT(20) NOT NULL COMMENT '',
  `status` INT NOT NULL COMMENT '订单状态:0-新建;10-确认收到;20-未收到',
  `delivery_com` VARCHAR(200) NOT NULL COMMENT '快递公司',
  `delivery_order_no` VARCHAR(200) NOT NULL COMMENT '快递单号',
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
	COMMENT = '还书表';