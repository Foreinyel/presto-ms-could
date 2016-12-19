package com.presto.api.user.ro;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/12/12.
 */
@Data
public class AccountRO {

    private Long accountId;    //acountId
    private BigDecimal amount;  //账户余额

}
