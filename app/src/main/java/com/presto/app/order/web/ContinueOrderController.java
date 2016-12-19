package com.presto.app.order.web;

import com.presto.api.order.service.ContinueOrderService;
import com.presto.api.order.vo.ContinueOrderVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shihao on 16/12/7.
 */
@RestController
@RequestMapping("/continueOrder")
public class ContinueOrderController {

    @Autowired
    private ContinueOrderService continueOrderService;

    @RequestMapping(value = "/loadItems", method = RequestMethod.GET)
    public JsonResult loadItems(@RequestParam Long orderId) {
        if (orderId == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        ContinueOrderVO vo = new ContinueOrderVO();
        vo.setOrderId(orderId);
        return JsonResult.success(continueOrderService.loadContinueOrderItems(vo));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody ContinueOrderVO vo) {
        if (vo.getOrderId() == null) {
            return JsonResult.failure("订单ID不能为空");
        }
        if (vo.getAmount() == null) {
            return JsonResult.failure("金额不能为空");
        }
        if (vo.getWeeks() == null) {
            return JsonResult.failure("时间不能为空");
        }

        int result = continueOrderService.continueOrder(vo);
        if (result != 0) {
            return JsonResult.failure("续惜阅失败");
        }
        return JsonResult.success("续惜阅成功");
    }

}
