package com.presto.api.order.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by shihao on 16/11/15.
 */
@Data
public class OrderVO {

    private Long id;
    @NotNull
    private Long userId;
    private Integer status;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Date dateFrom;
    @NotNull
    private Date dateEnd;
    private Date orderDate;

    private String name;
    private String mobile;
    private String address;
    private String note;

    private String sendOrderCom;    //配送公司
    private String sendOrderComOrderNo; //配送订单号

    private List<OrderDetailVO> orderDetails;

}
