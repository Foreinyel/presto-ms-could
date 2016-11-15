package com.presto.app.order.web;

import com.presto.api.order.entity.Order;
import com.presto.api.order.service.OrderService;
import com.presto.api.order.vo.OrderVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by shihao on 16/11/15.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public JsonResult createOrder(@Valid @RequestBody OrderVO vo){
        if(vo.getUserId()==null){
            return JsonResult.failure("用户ID不能为空");
        }
        if(vo.getDateFrom() == null || vo.getDateEnd() == null){
            return JsonResult.failure("日期范围不能为空");
        }
        if(vo.getAmount() == null){
            return JsonResult.failure("订单金额不能为空");
        }
        if(vo.getOrderDetails().size()==0){
            return JsonResult.failure("书籍明细不能为空");
        }

        Order order = orderService.createOrder(vo);
        return JsonResult.success(order);
    }

}
