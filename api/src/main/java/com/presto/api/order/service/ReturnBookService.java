package com.presto.api.order.service;

import com.presto.api.order.entity.ReturnBook;
import com.presto.api.order.vo.ReturnBookVO;

/**
 * Created by shihao on 16/12/6.
 */
public interface ReturnBookService {

    ReturnBook createReturnBook(ReturnBookVO vo);
    ReturnBook confirmReturn(ReturnBookVO vo);
    ReturnBook rejectReturn(ReturnBookVO vo);
}
