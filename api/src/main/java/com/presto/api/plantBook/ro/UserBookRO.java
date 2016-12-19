package com.presto.api.plantBook.ro;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/11/21.
 */
@Data
public class UserBookRO {

    private Long id;
    private Long bookId;
    private Long userId;
    private String userName;
    private Integer pickMethod;
    private String  pickMethodDesc;
    private String pickAddress;
    private BigDecimal price;

}
