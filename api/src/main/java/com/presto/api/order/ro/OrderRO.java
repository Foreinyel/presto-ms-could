package com.presto.api.order.ro;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by shihao on 16/11/15.
 */
@Data
public class OrderRO {

    private Long id;
    private Integer status;
    private Long userId;
    private BigDecimal amount;
    private Date dateFrom;
    private Date dateEnd;
    private Date orderDate;

}
