package com.presto.api.plantBook.dao.Impl;

import com.presto.api.plantBook.dao.UserBookDao;
import com.presto.api.plantBook.entity.UserBook;
import com.presto.api.plantBook.ro.UserBookRO;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.HashMap;

/**
 * Created by shihao on 16/11/7.
 */
@Repository
public class UserBookDaoImpl extends CommonDaoImpl implements UserBookDao {

    private static final String NAMESPACE = UserBook.class.getName() + ".";

    public List<UserBookRO> findByBookId(final Long bookId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("bookId", bookId);
        return getSqlSession().selectList(NAMESPACE + "findByBookId", param);
    }

}
