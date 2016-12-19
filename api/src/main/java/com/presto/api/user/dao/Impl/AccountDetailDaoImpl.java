package com.presto.api.user.dao.Impl;

import com.presto.api.user.dao.AccountDetailDao;
import com.presto.api.user.entity.AccountDetail;
import com.presto.api.user.ro.AccountRO;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.HashMap;

/**
 * Created by shihao on 16/12/9.
 */
@Repository
public class AccountDetailDaoImpl extends CommonDaoImpl implements AccountDetailDao {

    private static final String NAMESPACE = AccountDetail.class.getName() + ".";

    public AccountRO findUserAccountBalance(final Long userId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return getSqlSession().selectOne(NAMESPACE + "findUserAccountBalance", param);
    }

}
