CREATE TABLE `presto`.`b_order_detail` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `order_id` BIGINT(20) NOT NULL COMMENT '',
  `user_book_id` BIGINT(20) NOT NULL COMMENT '',
  `deleted_flag` INT(1) NULL COMMENT '',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '订单行表';

	ALTER TABLE `presto`.`b_order_detail`
ADD COLUMN `price` DECIMAL(20,4) NULL DEFAULT 0 COMMENT '书籍价格' AFTER `user_book_id`;