package com.presto.api.plantBook.dao;

import com.presto.api.plantBook.ro.UserBookRO;
import com.presto.common.dao.CommonDao;

import java.util.List;

/**
 * Created by shihao on 16/11/7.
 */
public interface UserBookDao extends CommonDao {

    List<UserBookRO> findByBookId(final Long bookId);
}
