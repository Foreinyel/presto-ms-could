package com.presto.api.book.service.Impl;

import com.presto.api.book.dao.BookDao;
import com.presto.api.book.ro.BookRO;
import com.presto.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by shihao on 16/11/13.
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    public List<BookRO> findBooks(){

        return bookDao.findBooks();
    }

}
