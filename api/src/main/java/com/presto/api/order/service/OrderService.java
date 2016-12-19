package com.presto.api.order.service;

import com.presto.api.order.entity.Order;
import com.presto.api.order.ro.OrderRO;
import com.presto.api.order.vo.OrderVO;

import java.util.List;

/**
 * Created by shihao on 16/11/15.
 */
public interface OrderService {

    Order createOrder(OrderVO vo);
    List<OrderRO> findOrdersByUser(OrderVO vo);
    OrderRO findOrderById(OrderVO vo);
    int orderPay(OrderVO vo);
    Order orderSend(OrderVO vo);
    Order bookReading(OrderVO vo);
    Order orderBacking(OrderVO vo);
    Order orderDone(OrderVO vo);
    Order cancelOrder(OrderVO vo);
    List<OrderRO> queryAllOrders(OrderVO vo);

}
