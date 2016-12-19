package com.presto.api.order.service.Impl;

import com.presto.api.order.dao.OrderDao;
import com.presto.api.order.dao.OrderDetailDao;
import com.presto.api.order.entity.Order;
import com.presto.api.order.entity.OrderDetail;
import com.presto.api.order.ro.OrderRO;
import com.presto.api.order.service.OrderService;
import com.presto.api.order.vo.OrderVO;
import com.presto.api.user.dao.AccountDetailDao;
import com.presto.api.user.entity.AccountDetail;
import com.presto.api.user.ro.AccountRO;
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

    @Autowired
    private AccountDetailDao accountDetailDao;

    public Order createOrder(OrderVO vo) {

        Order order = new Order();
        order.setStatus(CommonConstants.OrderStatus.PAYING);    //创建后直接设置为待支付状态
        order.setCreatedDate(new Date());
        order.setUserId(vo.getUserId());
        order.setDateFrom(vo.getDateFrom());
        order.setDateEnd(vo.getDateEnd());
        order.setOrderDate(new Date());
        order.setAmount(vo.getAmount());
        order.setName(vo.getName());
        order.setMobile(vo.getMobile());
        order.setAddress(vo.getAddress());
        order.setNote(vo.getNote());
        orderDao.insert(order);

//        BigDecimal totalAmount = BigDecimal.ZERO;
//        int days = (int)(vo.getDateFrom().getTime()-vo.getDateEnd().getTime()) / (1000 * 60 * 60 * 24);
        List<OrderDetail> orderDetails = new ArrayList<>();
        vo.getOrderDetails().stream().forEach(detail -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setCreatedDate(new Date());
            orderDetail.setUserBookId(detail.getUserBookId());
            orderDetail.setPrice(detail.getPrice());
            orderDetails.add(orderDetail);
        });
        orderDetailDao.batchInsert(orderDetails);

        return order;

    }

    public List<OrderRO> findOrdersByUser(OrderVO vo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", vo.getUserId());
        params.put("status", vo.getStatus());
        return orderDao.queryOrdersByUser(params);
    }

    public OrderRO findOrderById(OrderVO vo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("orderId", vo.getId());
        return orderDao.findOrderById(params);
    }

    public int orderPay(OrderVO vo) {    //支付后将订单设置为待发货状态
        Order order = orderDao.findById(Order.class, vo.getId());
        if (!order.getStatus().equals(CommonConstants.OrderStatus.PAYING)) {
            return -1;
        }

        //1.获取用户账户余额
        AccountRO accountRO = accountDetailDao.findUserAccountBalance(order.getUserId());
        if (order.getAmount().compareTo(accountRO.getAmount()) == 1) {       //余额不足
            return 1;
        }
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setSourceType(CommonConstants.AccountSourceType.OrderPay);
        accountDetail.setSourceTypeId(order.getId());
        accountDetail.setAmount(order.getAmount().multiply(new BigDecimal(-1)));
        accountDetail.setCreatedDate(new Date());
        accountDetail.setDeletedFlag(0);
        accountDetail.setUpdatedDate(new Date());
        accountDetail.setAccountId(accountRO.getAccountId());
        accountDetailDao.insert(accountDetail);


        order.setStatus(CommonConstants.OrderStatus.SENDING);
        order.setPayDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return 0;
    }

    public Order orderSend(OrderVO vo) {     //讲订单状态改为配送中状态
        Order order = orderDao.findById(Order.class, vo.getId());
        order.setStatus(CommonConstants.OrderStatus.SEND);
        order.setSendOrderCom(vo.getSendOrderCom());
        order.setSendOrderComOrderNo(vo.getSendOrderComOrderNo());
        order.setSendDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public Order bookReading(OrderVO vo) {   //订单更新为惜阅中
        Order order = orderDao.findById(Order.class, vo.getId());
        order.setStatus(CommonConstants.OrderStatus.READING);
        order.setReadingDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;

    }

    public Order orderBacking(OrderVO vo) {      //待归还日期
        Order order = orderDao.findById(Order.class, vo.getId());
        order.setStatus(CommonConstants.OrderStatus.BACKING);
        order.setBackingDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public Order orderDone(OrderVO vo) {
        Order order = orderDao.findById(Order.class, vo.getId());
        order.setStatus(CommonConstants.OrderStatus.DONE);
        order.setDoneDate(new Date());
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public Order cancelOrder(OrderVO vo) {
        Order order = orderDao.findById(Order.class, vo.getId());
        order.setStatus(CommonConstants.OrderStatus.CANCEL);
        order.setUpdatedDate(new Date());
        orderDao.update(order);
        return order;
    }

    public List<OrderRO> queryAllOrders(OrderVO vo) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("status", vo.getStatus());
        return orderDao.queryAllOrders(param);
    }

}
