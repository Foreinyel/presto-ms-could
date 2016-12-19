package com.presto.api.user.service;

import com.presto.api.user.vo.AccountVO;
import com.presto.api.user.vo.RechargeVO;

/**
 * Created by shihao on 16/12/9.
 */
public interface AccountService {

    int createAccount(AccountVO vo);

    int recharge(RechargeVO vo);
}
