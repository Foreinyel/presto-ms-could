CREATE TABLE `presto`.`b_plant_book_req` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '',
  `status` INT(1) NOT NULL COMMENT '状态:0,新建；1，审批通过；2，审批不通过',
  `plant_type` INT(1) NOT NULL COMMENT '植书类型：0-赠与平台（书籍归平台所有）；1-分享到平台（平台代管书籍）；2-个人分享者（书籍自己管理）',
  `plant_method` INT(1) NOT NULL COMMENT '植书方式：0，扫码植书；1，搜索植书；2，手工植书',
  `book_id` BIGINT(20) NULL COMMENT '关联b_books书库表，扫码植书和搜索植书用到',
  `book_name` VARCHAR(45) NULL COMMENT '书名,手工植书用到',
  `book_author` VARCHAR(45) NULL COMMENT '书作者,手工植书用到',
  `book_press` VARCHAR(45) NULL COMMENT '出版社，手工植书用到',
  `book_isbn` VARCHAR(45) NULL COMMENT 'isbn号，手工植书用到',
  `book_img_url` VARCHAR(300) NULL COMMENT '书籍封面，手工植书用到',
  `deleted_flag` INT(1) NULL DEFAULT 0 COMMENT '',
  `created_by` BIGINT(20) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_by` BIGINT(20) NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  `version` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
  ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COMMENT = '植书申请表'
