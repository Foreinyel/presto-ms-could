package com.presto.api.book.dao;

import com.presto.api.book.ro.BookRO;
import com.presto.common.dao.CommonDao;

import java.util.List;

/**
 * Created by shihao on 16/11/7.
 */
public interface BookDao extends CommonDao {

    List<BookRO> findBooks();
}
