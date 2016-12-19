package com.presto.api.book.dao;

import com.presto.api.book.ro.BookRO;
import com.presto.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * Created by shihao on 16/11/7.
 */
public interface BookDao extends CommonDao {

    List<BookRO> findBooks();
    BookRO findBookById(final Long bookId);
    List<BookRO> findAllBooks(Map<String, Object> param);
}
