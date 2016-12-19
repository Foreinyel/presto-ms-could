package com.presto.app.order.web;

import com.presto.api.order.entity.Order;
import com.presto.api.order.service.OrderService;
import com.presto.api.order.vo.OrderVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by shihao on 16/11/15.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult createOrder(@Valid @RequestBody OrderVO vo) {
        if (vo.getUserId() == null) {
            return JsonResult.failure("用户ID不能为空");
        }
        if (vo.getDateFrom() == null || vo.getDateEnd() == null) {
            return JsonResult.failure("日期范围不能为空");
        }
        if (vo.getAmount() == null) {
            return JsonResult.failure("订单金额不能为空");
        }
        if (vo.getOrderDetails().size() == 0) {
            return JsonResult.failure("书籍明细不能为空");
        }

        Order order = orderService.createOrder(vo);
        return JsonResult.success(order);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult listOrderByUser(@RequestParam Long userId, @RequestParam Integer status) {
        if (userId == null) {
            return JsonResult.failure("用户ID不能为空");
        }
        OrderVO vo = new OrderVO();
        vo.setUserId(userId);
        vo.setStatus(status);
        return JsonResult.success(orderService.findOrdersByUser(vo));
    }

    @RequestMapping(value = "/listById", method = RequestMethod.GET)
    public JsonResult listOrderById(@RequestParam Long orderId) {
        if (orderId == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        OrderVO vo = new OrderVO();
        vo.setId(orderId);
        return JsonResult.success(orderService.findOrderById(vo));

    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public JsonResult cancelOrder(@RequestParam Long id) {
        if (id == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        OrderVO vo = new OrderVO();
        vo.setId(id);
        return JsonResult.success(orderService.cancelOrder(vo));
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public JsonResult payOrder(@RequestParam Long id) {
        if (id == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        OrderVO vo = new OrderVO();
        vo.setId(id);
        int result = orderService.orderPay(vo);
        if (result == -1) {
            return JsonResult.failure("订单状态有误");
        }
        if (result == 1) {
            return JsonResult.failure("账户余额不足");
        }
        return JsonResult.success("支付成功");
    }

}
