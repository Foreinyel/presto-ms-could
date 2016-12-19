DROP TABLE IF EXISTS `presto`.`u_recharge_item`;
CREATE TABLE `presto`.`u_recharge_item` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `rmb_amount` DECIMAL(20,4) not null comment '人民币金额',
  `amount` DECIMAL(20,4) not null comment '书币金额',
  `status` INT NOT NULL DEFAULT 0 COMMENT '状态:0-新建;1-正常',
  `deleted_flag` INT(1) NOT NULL DEFAULT 0 COMMENT '',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_account_id` (`id` ASC))
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '充值items';




INSERT INTO u_recharge_item(rmb_amount,amount,status) VALUE (20,20,1);
INSERT INTO u_recharge_item(rmb_amount,amount,status) VALUE (50,50,1);
INSERT INTO u_recharge_item(rmb_amount,amount,status) VALUE (100,100,1);
INSERT INTO u_recharge_item(rmb_amount,amount,status) VALUE (300,300,1);