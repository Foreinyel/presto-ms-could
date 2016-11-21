CREATE TABLE `presto`.`b_books` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `book_name` VARCHAR(100) NOT NULL COMMENT '书名',
  `book_img_url` VARCHAR(300) NULL COMMENT 'book图片地址',
  `book_version` VARCHAR(45) NULL COMMENT '书籍版本号',
  `book_issn` VARCHAR(45) NULL COMMENT '',
  `book_isbn` VARCHAR(45) NOT NULL COMMENT '',
  `book_asin` VARCHAR(45) NULL COMMENT '',
  `book_author` VARCHAR(45) NULL COMMENT '作者',
  `book_desc` TEXT(65000) NULL COMMENT '简介',
  `book_price` VARCHAR(45) NULL COMMENT '定价',
  `book_press` VARCHAR(45) NULL COMMENT '出版社',
  `book_url` VARCHAR(45) NULL COMMENT '书籍来源url',
  `book_src` VARCHAR(45) NULL COMMENT '书籍来源站点',
  `deleted_flag` int(1) NOT NULL DEFAULT '0',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
   PRIMARY KEY (id),
   INDEX `idx_book_id` (`id` ASC))
    ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '书';

ALTER TABLE `presto`.`b_books`
ADD COLUMN `price` DECIMAL(20,4) NULL DEFAULT 0 COMMENT '书籍价格:￥/day' AFTER `book_src`;