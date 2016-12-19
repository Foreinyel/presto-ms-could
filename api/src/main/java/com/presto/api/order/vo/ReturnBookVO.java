package com.presto.api.order.vo;

import lombok.Data;

/**
 * Created by shihao on 16/12/6.
 */
@Data
public class ReturnBookVO {

    private Long id;
    private Long userId;
    private Long orderId;
    private String deliveryCom;
    private String deliveryOrderNo;

}
