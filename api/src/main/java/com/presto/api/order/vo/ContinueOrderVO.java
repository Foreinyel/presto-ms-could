package com.presto.api.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/12/7.
 */
@Data
public class ContinueOrderVO {

    private Long orderId;
    private Integer weeks;
    private BigDecimal amount;

}
