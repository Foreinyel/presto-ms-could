package com.presto.api.user.service;

import com.presto.api.user.entity.User;
import com.presto.api.user.vo.UserVo;

/**
 * Created by shihao on 16/10/29.
 */
public interface UserService {

    User findById(Long id);
    User findByMobile(final String mobile);
    User register(final UserVo vo);
    User login(final UserVo vo);

}
