package com.presto.api.order.ro;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by shihao on 16/11/15.
 */
@Data
public class OrderRO {

    private Long id;
    private Integer status;
    private String statusDesc;
    private Long userId;
    private BigDecimal amount;
    private Date dateFrom;
    private Date dateEnd;
    private Date orderDate;
    private String mobile;  //用户手机号
    private String name;
    private String address;
    private String note;
    private Date sendDate;
    private String sendOrderCom;
    private String sendOrderComOrderNo;

    private List<OrderDetailRO> orderDetails;

}
