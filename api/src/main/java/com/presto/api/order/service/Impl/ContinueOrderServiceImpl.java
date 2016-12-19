package com.presto.api.order.service.Impl;

import com.presto.api.order.dao.ContinueOrderDao;
import com.presto.api.order.dao.OrderDao;
import com.presto.api.order.entity.ContinueOrder;
import com.presto.api.order.entity.Order;
import com.presto.api.order.ro.ContinueOrderItemRO;
import com.presto.api.order.ro.OrderRO;
import com.presto.api.order.service.ContinueOrderService;
import com.presto.api.order.vo.ContinueOrderVO;
import com.presto.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shihao on 16/12/7.
 */
@Service
public class ContinueOrderServiceImpl implements ContinueOrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ContinueOrderDao continueOrderDao;

    public List<ContinueOrderItemRO> loadContinueOrderItems(ContinueOrderVO vo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("orderId", vo.getOrderId());
        OrderRO orderRO = orderDao.findOrderById(params);
        final BigDecimal[] totalPricePerday = {BigDecimal.ZERO};
        orderRO.getOrderDetails().stream().forEach(detail -> {
            totalPricePerday[0].add(detail.getPrice());
        });

        List<ContinueOrderItemRO> itemROList = new ArrayList<>();

        ContinueOrderItemRO oneWeek = new ContinueOrderItemRO();
        oneWeek.setAmount(totalPricePerday[0].multiply(BigDecimal.valueOf(7L)));
        oneWeek.setWeeks(1);
        itemROList.add(oneWeek);

        ContinueOrderItemRO twoWeek = new ContinueOrderItemRO();
        twoWeek.setAmount(totalPricePerday[0].multiply(BigDecimal.valueOf(14L)));
        twoWeek.setWeeks(2);
        itemROList.add(twoWeek);

        ContinueOrderItemRO threeWeek = new ContinueOrderItemRO();
        threeWeek.setAmount(totalPricePerday[0].multiply(BigDecimal.valueOf(21L)));
        threeWeek.setWeeks(3);
        itemROList.add(threeWeek);

        ContinueOrderItemRO fourWeek = new ContinueOrderItemRO();
        fourWeek.setAmount(totalPricePerday[0].multiply(BigDecimal.valueOf(28L)));
        fourWeek.setWeeks(4);
        itemROList.add(fourWeek);

        return itemROList;
    }

    public int continueOrder(ContinueOrderVO vo) {
        Order order = orderDao.findById(Order.class, vo.getOrderId());
        //1.付款

        //2.付款成功后修改订单数据
        Integer totalDays = vo.getWeeks() * 7;
        Date oldDateEnd = order.getDateEnd();
        Date newDateEnd = new Date(oldDateEnd.getTime() + totalDays * 24 * 60 * 60 * 1000);
        BigDecimal oldTotalAmount = order.getAmount();
        BigDecimal newTotalAmount = oldTotalAmount.add(vo.getAmount());
        order.setDateEnd(newDateEnd);
        order.setAmount(newTotalAmount);
        order.setUpdatedDate(new Date());
        order.setStatus(CommonConstants.OrderStatus.READING);
        orderDao.update(order);

        //3.插入续惜阅记录
        ContinueOrder continueOrder = new ContinueOrder();
        continueOrder.setOrderId(vo.getOrderId());
        continueOrder.setCreatedDate(new Date());
        continueOrder.setUpdatedDate(new Date());
        continueOrder.setContinueDays(vo.getWeeks() * 7);
        continueOrder.setContinueAmount(vo.getAmount());
        continueOrder.setUserId(order.getUserId());
        continueOrder.setDeletedFlag(0);
        continueOrderDao.insert(continueOrder);

        return 0;

    }

}
