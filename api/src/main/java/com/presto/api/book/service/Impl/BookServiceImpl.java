package com.presto.api.book.service.Impl;

import com.presto.api.book.dao.BookDao;
import com.presto.api.book.entity.Books;
import com.presto.api.book.ro.BookRO;
import com.presto.api.book.service.BookService;
import com.presto.api.book.vo.BookVO;
import com.presto.api.plantBook.dao.UserBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.HashMap;

/**
 * Created by shihao on 16/11/13.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserBookDao userBookDao;

    public List<BookRO> findBooks() {

        return bookDao.findBooks();
    }

    public BookRO findBookById(final Long bookId) {
        BookRO bookRO = bookDao.findBookById(bookId);
        bookRO.setUserBookROList(userBookDao.findByBookId(bookId));
        return bookRO;
    }

    public List<BookRO> findAllBook(final BookVO vo) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("bookName", vo.getBookName());
        param.put("bookAuthor", vo.getBookAuthor());
        param.put("bookPress", vo.getBookPress());
        param.put("bookIsbn", vo.getBookIsbn());
        return bookDao.findAllBooks(param);
    }

    public int updateBook(final BookVO vo) {

        Books books = bookDao.findById(Books.class, vo.getId());
        books.setBookImgUrl(vo.getBookImgUrl());
        books.setBookName(vo.getBookName());
        books.setBookIsbn(vo.getBookIsbn());
        books.setBookPress(vo.getBookPress());
        books.setBookAuthor(vo.getBookAuthor());
        books.setBookDesc(vo.getBookDesc());
        books.setBookPrice(vo.getBookPrice());
        books.setPrice(vo.getPrice());
        books.setUpdatedDate(new Date());
        bookDao.update(books);

        return 0;
    }
}
