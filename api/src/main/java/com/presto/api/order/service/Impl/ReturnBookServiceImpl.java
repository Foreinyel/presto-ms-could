package com.presto.api.order.service.Impl;

import com.presto.api.order.dao.OrderDao;
import com.presto.api.order.dao.ReturnBookDao;
import com.presto.api.order.entity.Order;
import com.presto.api.order.entity.ReturnBook;
import com.presto.api.order.service.ReturnBookService;
import com.presto.api.order.vo.ReturnBookVO;
import com.presto.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shihao on 16/12/6.
 */
@Service
public class ReturnBookServiceImpl implements ReturnBookService {

    @Autowired
    private ReturnBookDao returnBookDao;

    @Autowired
    private OrderDao orderDao;

    public ReturnBook createReturnBook(ReturnBookVO vo){
        ReturnBook returnBook = new ReturnBook();
        returnBook.setStatus(0);
        returnBook.setCreatedDate(new Date());
        returnBook.setOrderId(vo.getOrderId());
        returnBook.setUserId(vo.getUserId());
        returnBook.setDeliveryCom(vo.getDeliveryCom());
        returnBook.setDeliveryOrderNo(vo.getDeliveryOrderNo());
        returnBookDao.insert(returnBook);

        return returnBook;
    }

    //确认收到书籍
    public ReturnBook confirmReturn(ReturnBookVO vo){
        ReturnBook returnBook = returnBookDao.findById(ReturnBook.class,vo.getId());
        returnBook.setStatus(CommonConstants.ReturnBookStatus.CONFIRM);
        returnBook.setUpdatedDate(new Date());

        //更新订单状态
        Order order = orderDao.findById(Order.class,returnBook.getOrderId());
        order.setStatus(CommonConstants.OrderStatus.DONE);
        order.setDoneDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);

        //解锁书籍

        returnBookDao.update(returnBook);

        return returnBook;
    }

    //未收到书籍
    public ReturnBook rejectReturn(ReturnBookVO vo){
        ReturnBook returnBook = returnBookDao.findById(ReturnBook.class,vo.getId());
        returnBook.setStatus(CommonConstants.ReturnBookStatus.REJECT);
        returnBook.setUpdatedDate(new Date());
        return returnBook;
    }

}
