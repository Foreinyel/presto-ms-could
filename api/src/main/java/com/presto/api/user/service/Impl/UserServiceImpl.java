package com.presto.api.user.service.Impl;

import com.presto.api.user.dao.InviteCodeDao;
import com.presto.api.user.dao.UserDao;
import com.presto.api.user.entity.InviteCode;
import com.presto.api.user.entity.User;
import com.presto.api.user.service.UserService;
import com.presto.api.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shihao on 16/10/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InviteCodeDao inviteCodeDao;

    public User findById(final Long id){
        User user = new User();
        user.setId(id);
        user = userDao.searchOne(user);
        return user;
    }

    //按手机号查询用户
    public User findByMobile(final String mobile){
        User user = new User();
        user.setMobile(mobile);
        user = userDao.searchOne(user);
        return user;
    }

    public User register(final UserVo vo){
        //1.更新邀请码状态
        InviteCode inviteCode = new InviteCode();
        inviteCode.setCode(vo.getInviteCode());;
        inviteCode = inviteCodeDao.searchOne(inviteCode);
        inviteCode.setStatus(1);
        inviteCodeDao.update(inviteCode);

        //2.新增用户
        User user = new User();
        user.setPasswd("000000");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setMobile(vo.getMobile());
        user.setInviteCodeId(inviteCode.getId());
        user.setDeletedFlag(0);

        userDao.insert(user);
        return user;
    }

    public User login(final UserVo vo){
        User user = findByMobile(vo.getMobile());
        if (!user.getPasswd().equals(vo.getPasswd())){
            return null;
        }
        return user;
    }

}
