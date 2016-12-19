package com.presto.api.order.ro;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/12/7.
 */
@Data
public class ContinueOrderItemRO {
    private Integer weeks;
    private BigDecimal amount;
}
