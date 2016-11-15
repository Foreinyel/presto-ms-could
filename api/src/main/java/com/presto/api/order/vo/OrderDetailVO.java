package com.presto.api.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/11/15.
 */
@Data
public class OrderDetailVO {

    private Long id;
    private Long userBookId;
    private BigDecimal price;

}
