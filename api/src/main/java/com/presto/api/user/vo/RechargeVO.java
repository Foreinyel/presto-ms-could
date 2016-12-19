package com.presto.api.user.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/12/12.
 */

@Data
public class RechargeVO {

    private Long id;    //recharge item id

    private BigDecimal rmb_amount;  //    rmb金额

    private BigDecimal amount;   //对应书币金额

    private Long userId;

}
