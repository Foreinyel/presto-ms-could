package com.presto.api.book.service;

import com.presto.api.book.ro.BookRO;
import com.presto.api.book.vo.BookVO;

import java.util.List;

/**
 * Created by shihao on 16/11/13.
 */
public interface BookService {

    List<BookRO> findBooks();
    BookRO findBookById(final Long bookId);
    List<BookRO> findAllBook(final BookVO vo);
    int updateBook(final BookVO vo);
}
