CREATE TABLE `presto`.`b_user_book` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `book_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '书籍ID',
  `user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `deleted_flag` int(1) NOT NULL DEFAULT '0',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
   PRIMARY KEY (id),
   INDEX `idx_b_user_book_id` (`id` ASC))
    ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '用户分享书籍表';

ALTER TABLE `presto`.`b_user_book`
ADD COLUMN `pick_method` INT NULL COMMENT '取书方式:0-上门自取,1-平台配送' AFTER `user_id`;
ALTER TABLE `presto`.`b_user_book`
ADD COLUMN `pick_address` VARCHAR(200) NULL COMMENT '取书地址' AFTER `pick_method`;