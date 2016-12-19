package com.presto.api.book.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/12/14.
 */
@Data
public class BookVO {

    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookPress;
    private String bookIsbn;
    private String bookImgUrl;
    private String bookDesc;
    private String bookPrice;
    private BigDecimal price;

}
