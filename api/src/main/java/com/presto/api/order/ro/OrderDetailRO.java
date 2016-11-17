package com.presto.api.order.ro;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/11/16.
 */
@Data
public class OrderDetailRO {

    private Long id;
    private Long userBookId;
    private Long bookId;
    private Long bookName;
    private String bookAuthor;
    private BigDecimal price;
    private String bookPress;
    private String bookIsbn;

}
