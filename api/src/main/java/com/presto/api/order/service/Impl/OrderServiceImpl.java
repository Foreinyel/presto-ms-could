package com.presto.api.order.service.Impl;

import com.presto.api.order.dao.OrderDao;
import com.presto.api.order.dao.OrderDetailDao;
import com.presto.api.order.entity.Order;
import com.presto.api.order.entity.OrderDetail;
import com.presto.api.order.ro.OrderRO;
import com.presto.api.order.service.OrderService;
import com.presto.api.order.vo.OrderVO;
import com.presto.common.constants.CommonConstants;
import com.presto.common.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shihao on 16/11/15.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public Order createOrder(OrderVO vo){

        Order order = new Order();
        order.setStatus(CommonConstants.OrderStatus.PAYING);    //创建后直接设置为待支付状态
        order.setCreatedDate(new Date());
        order.setUserId(vo.getUserId());
        order.setDateFrom(vo.getDateFrom());
        order.setDateEnd(vo.getDateEnd());
        order.setOrderDate(new Date());
        order.setAmount(vo.getAmount());
        orderDao.insert(order);

//        BigDecimal totalAmount = BigDecimal.ZERO;
//        int days = (int)(vo.getDateFrom().getTime()-vo.getDateEnd().getTime()) / (1000 * 60 * 60 * 24);
        List<OrderDetail> orderDetails = new ArrayList<>();
        vo.getOrderDetails().stream().forEach(detail ->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setCreatedDate(new Date());
            orderDetail.setUserBookId(detail.getUserBookId());
            orderDetails.add(orderDetail);
        });
        orderDetailDao.batchInsert(orderDetails);

        return order;

    }

    public List<OrderRO> findOrdersByUser(OrderVO vo){
        HashMap<String,Object> params = new HashMap<>();
        params.put("userId",vo.getUserId());
        params.put("status",vo.getStatus());
        return orderDao.queryOrdersByUser(params);
    }

    public Order orderPay(OrderVO vo){    //支付后将订单设置为待发货状态
        Order order = orderDao.findById(Order.class,vo.getId());
        order.setStatus(CommonConstants.OrderStatus.SENDING);
        order.setPayDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public Order orderSend(OrderVO vo){     //讲订单状态改为配送中状态
        Order order = orderDao.findById(Order.class,vo.getId());
        order.setStatus(CommonConstants.OrderStatus.SEND);
        order.setSendDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public Order bookReading(OrderVO vo){   //订单更新为喜悦中
        Order order = orderDao.findById(Order.class,vo.getId());
        order.setStatus(CommonConstants.OrderStatus.READING);
        order.setReadingDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;

    }

    public Order orderBacking(OrderVO vo){      //待归还日期
        Order order = orderDao.findById(Order.class,vo.getId());
        order.setStatus(CommonConstants.OrderStatus.BACKING);
        order.setBackingDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public Order orderDone(OrderVO vo){
        Order order = orderDao.findById(Order.class,vo.getId());
        order.setStatus(CommonConstants.OrderStatus.DONE);
        order.setDoneDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

}
