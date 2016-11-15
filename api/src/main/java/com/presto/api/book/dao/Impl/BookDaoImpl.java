package com.presto.api.book.dao.Impl;

import com.presto.api.book.dao.BookDao;
import com.presto.api.book.entity.Books;
import com.presto.api.book.ro.BookRO;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by shihao on 16/11/7.
 */
@Repository
public class BookDaoImpl extends CommonDaoImpl implements BookDao{

    private static final String NAMESPACE = Books.class.getName() + ".";

    public List<BookRO> findBooks(){
       return getSqlSession().selectList(NAMESPACE);
    }

}
