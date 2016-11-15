package com.presto.api.order.service;

import com.presto.api.order.entity.Order;
import com.presto.api.order.vo.OrderVO;

/**
 * Created by shihao on 16/11/15.
 */
public interface OrderService {

    Order createOrder(OrderVO vo);
}
