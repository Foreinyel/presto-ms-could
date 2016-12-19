package com.presto.op.book.web;

import com.presto.api.book.service.BookService;
import com.presto.api.book.vo.BookVO;
import com.presto.common.result.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shihao on 16/12/14.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JsonResult findAllBooks(@RequestBody BookVO vo) {
        return JsonResult.success(bookService.findAllBook(vo));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateBook(@RequestBody BookVO vo) {
        if (vo.getId() == null) {
            return JsonResult.failure("书籍ID不能为空");
        }
        if (StringUtils.isBlank(vo.getBookName())) {
            return JsonResult.failure("书名不能为空");
        }
        if (StringUtils.isBlank(vo.getBookPress())) {
            return JsonResult.failure("出版社不能为空");
        }
        if (StringUtils.isBlank(vo.getBookIsbn())) {
            return JsonResult.failure("ISBN不能为空");
        }
        if (StringUtils.isBlank(vo.getBookAuthor())) {
            return JsonResult.failure("作者不能为空");
        }
        if (vo.getPrice() == null) {
            return JsonResult.failure("价格不能为空");
        }
        return JsonResult.success(bookService.updateBook(vo));
    }

}
