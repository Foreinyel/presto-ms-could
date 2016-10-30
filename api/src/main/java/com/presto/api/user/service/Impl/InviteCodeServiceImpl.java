package com.presto.api.user.service.Impl;

import com.presto.api.user.dao.InviteCodeDao;
import com.presto.api.user.entity.InviteCode;
import com.presto.api.user.service.InviteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shihao on 16/10/30.
 */
@Service
public class InviteCodeServiceImpl implements InviteCodeService {

    @Autowired
    private InviteCodeDao inviteCodeDao;

    public InviteCode findByCode(final String code){
        InviteCode inviteCode = new InviteCode();
        inviteCode.setCode(code);
        inviteCode.setStatus(0);
        inviteCode.setDeletedFlag(0);
        inviteCode = inviteCodeDao.searchOne(inviteCode);
        return inviteCode;
    }

}
