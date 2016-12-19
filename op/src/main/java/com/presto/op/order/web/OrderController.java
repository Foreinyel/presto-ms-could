package com.presto.op.order.web;

import com.presto.api.order.service.OrderService;
import com.presto.api.order.vo.OrderVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shihao on 16/12/14.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult listAll(@RequestBody OrderVO vo) {
        return JsonResult.success(orderService.queryAllOrders(vo));
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public JsonResult sendOrder(@RequestBody OrderVO vo) {
        if (vo.getId() == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        return JsonResult.success(orderService.orderSend(vo));
    }

    @RequestMapping(value = "/sendDone", method = RequestMethod.GET)     //确认送达,更新订单为惜阅中
    public JsonResult sendDone(@RequestParam Long orderId) {
        if (orderId == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        OrderVO vo = new OrderVO();
        vo.setId(orderId);
        return JsonResult.success(orderService.bookReading(vo));
    }

    @RequestMapping(value = "/done",method = RequestMethod.GET)     //确认收到书籍
    public JsonResult orderDone(@RequestParam Long orderId){
        if (orderId == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        OrderVO vo = new OrderVO();
        vo.setId(orderId);
        return JsonResult.success(orderService.orderDone(vo));

    }


}
