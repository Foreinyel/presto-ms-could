package com.presto.app.book.web;

import com.presto.api.book.service.BookService;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shihao on 16/11/19.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/findBooks",method = RequestMethod.GET)
    public JsonResult findBooks(){
        return JsonResult.success(bookService.findBooks());
    }

    @RequestMapping(value = "/findBookById",method = RequestMethod.GET)
    public JsonResult findBookById(@RequestParam Long bookId){
        if(bookId == null){
            return JsonResult.failure("bookId不能为空");
        }
        return JsonResult.success(bookService.findBookById(bookId));
    }
}
