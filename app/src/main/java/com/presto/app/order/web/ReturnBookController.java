package com.presto.app.order.web;

import com.presto.api.order.service.ReturnBookService;
import com.presto.api.order.vo.ReturnBookVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shihao on 16/12/6.
 */
@RestController
@RequestMapping("/returnBook")
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody ReturnBookVO vo) {
        if (vo.getDeliveryCom() == null) {
            return JsonResult.failure("快递公司不能为空");
        }
        if (vo.getDeliveryOrderNo() == null) {
            return JsonResult.failure("快递单号不能为空");
        }
        if (vo.getUserId() == null) {
            return JsonResult.failure("用户ID不能为空");
        }
        if (vo.getOrderId() == null) {
            return JsonResult.failure("订单编号不能为空");
        }
        return JsonResult.success(returnBookService.createReturnBook(vo));
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public JsonResult confirm(@RequestParam Long id) {
        if (id == null) {
            return JsonResult.failure("ID不能为空");
        }
        ReturnBookVO returnBookVO = new ReturnBookVO();
        returnBookVO.setId(id);
        return JsonResult.success(returnBookService.confirmReturn(returnBookVO));
    }


    @RequestMapping(value = "/reject", method = RequestMethod.GET)
    public JsonResult reject(@RequestParam Long id) {
        if (id == null) {
            return JsonResult.failure("ID不能为空");
        }
        ReturnBookVO returnBookVO = new ReturnBookVO();
        returnBookVO.setId(id);
        return JsonResult.success(returnBookService.rejectReturn(returnBookVO));
    }

}
