package com.presto.api.order.service.Impl;

import com.presto.api.order.dao.OrderDao;
import com.presto.api.order.dao.OrderDetailDao;
import com.presto.api.order.entity.Order;
import com.presto.api.order.entity.OrderDetail;
import com.presto.api.order.service.OrderService;
import com.presto.api.order.vo.OrderVO;
import com.presto.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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

}
