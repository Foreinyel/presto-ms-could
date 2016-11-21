package com.presto.api.book.service;

import com.presto.api.book.ro.BookRO;

import java.util.List;

/**
 * Created by shihao on 16/11/13.
 */
public interface BookService {

    List<BookRO> findBooks();
    BookRO findBookById(final Long bookId);
}
