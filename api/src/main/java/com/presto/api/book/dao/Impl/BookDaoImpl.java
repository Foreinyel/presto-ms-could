package com.presto.api.book.dao.Impl;

import com.presto.api.book.dao.BookDao;
import com.presto.api.book.entity.Books;
import com.presto.api.book.ro.BookRO;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shihao on 16/11/7.
 */
@Repository
public class BookDaoImpl extends CommonDaoImpl implements BookDao {

    private static final String NAMESPACE = Books.class.getName() + ".";

    public List<BookRO> findBooks() {
        return getSqlSession().selectList(NAMESPACE + "queryBooks");
    }

    public BookRO findBookById(final Long bookId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("bookId", bookId);
        return getSqlSession().selectOne(NAMESPACE + "findBookById", param);
    }

    public List<BookRO> findAllBooks(Map<String, Object> param) {
        return getSqlSession().selectList(NAMESPACE + "findAllBooks", param);
    }

}
