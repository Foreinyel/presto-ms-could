package com.presto.api.user.dao;

import com.presto.api.user.ro.AccountRO;
import com.presto.common.dao.CommonDao;

import java.math.BigDecimal;

/**
 * Created by shihao on 16/12/9.
 */
public interface AccountDetailDao extends CommonDao {
    AccountRO findUserAccountBalance(final Long userId);
}
