DROP TABLE IF EXISTS `presto`.`b_order`;
CREATE TABLE `presto`.`b_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` BIGINT(20) NOT NULL COMMENT '',
  `status` INT NOT NULL COMMENT '订单状态:0-新建;10-待支付;20-待发货;30-配送中;40-惜阅中;50-待归还;',
  `amount` DECIMAL NOT NULL COMMENT '订单总价',
  `date_from` DATETIME NOT NULL COMMENT '借阅日期从',
  `date_end` DATETIME NOT NULL COMMENT '借阅日期到',
  `order_date` DATETIME NOT NULL COMMENT '下单日期',
  `pay_date` DATETIME COMMENT '支付日期',
  `send_date` DATETIME COMMENT '配送日期',
  `reading_date` DATETIME COMMENT '开始惜阅日期',
  `backing_date` DATETIME COMMENT '待归还日期',
  `done_date` DATETIME COMMENT '订单完成日期',
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
	COMMENT = '订单头表';

ALTER TABLE `presto`.`b_order`
ADD COLUMN `note` VARCHAR(200) NULL DEFAULT '' COMMENT '备注' AFTER `done_date`;
ALTER TABLE `presto`.`b_order`
ADD COLUMN `name` VARCHAR(45) NULL DEFAULT '' COMMENT '联系方式' AFTER `user_id`;
ALTER TABLE `presto`.`b_order`
ADD COLUMN `mobile` VARCHAR(45) NULL DEFAULT '' COMMENT '联系方式-电话' AFTER `user_id`;
ALTER TABLE `presto`.`b_order`
ADD COLUMN `address` VARCHAR(200) NULL DEFAULT '' COMMENT '联系方式-地址' AFTER `user_id`;

--配送字段
ALTER TABLE `presto`.`b_order`
ADD COLUMN `send_order_com` VARCHAR(45) NULL DEFAULT '' COMMENT '配送公司' AFTER `send_date`;
ALTER TABLE `presto`.`b_order`
ADD COLUMN `send_order_com_order_no` VARCHAR(45) NULL DEFAULT '' COMMENT '配送订单号' AFTER `send_order_com`;