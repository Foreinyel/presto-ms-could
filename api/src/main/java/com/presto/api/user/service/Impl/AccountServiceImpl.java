package com.presto.api.user.service.Impl;

import com.presto.api.user.dao.AccountDao;
import com.presto.api.user.dao.AccountDetailDao;
import com.presto.api.user.dao.RechargeItemDao;
import com.presto.api.user.entity.Account;
import com.presto.api.user.entity.AccountDetail;
import com.presto.api.user.entity.RechargeItem;
import com.presto.api.user.service.AccountService;
import com.presto.api.user.vo.AccountVO;
import com.presto.api.user.vo.RechargeVO;
import com.presto.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shihao on 16/12/9.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountDetailDao accountDetailDao;

    @Autowired
    private RechargeItemDao rechargeItemDao;

    //创建账户
    public int createAccount(AccountVO vo) {
        Account account = new Account();
        account.setUserId(vo.getUserId());
        if (accountDao.searchOne(account) != null) {        //校验账户是否已存在
            return -1;
        }
        account.setStatus(0);
        account.setCreatedDate(new Date());
        account.setUpdatedDate(new Date());
        accountDao.insert(account);
        return 0;
    }

    //充值
    public int recharge(RechargeVO vo) {
        RechargeItem rechargeItem = rechargeItemDao.findById(RechargeItem.class, vo.getId());
        Account account = new Account();
        account.setUserId(vo.getUserId());
        account.setDeletedFlag(0);
        account = accountDao.searchOne(account);
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setDeletedFlag(0);
        accountDetail.setUpdatedDate(new Date());
        accountDetail.setCreatedDate(new Date());
        accountDetail.setAccountId(account.getId());
        accountDetail.setAmount(rechargeItem.getAmount());
        accountDetail.setSourceType(CommonConstants.AccountSourceType.Recharge_WX); //微信充值
        accountDetail.setSourceTypeId(rechargeItem.getId());                        //充值对应rechargeItem

        accountDetailDao.insert(accountDetail);
        return 0;
    }

}
