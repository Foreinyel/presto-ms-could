package com.presto.api.user.dao.Impl;

import com.presto.api.user.dao.UserDao;
import com.presto.api.user.entity.User;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by shihao on 16/10/29.
 */
@Repository
public class UserDaoImpl extends CommonDaoImpl implements UserDao {

    private static final String NAMESPACE = User.class.getName() + ".";

}
