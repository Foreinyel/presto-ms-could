package com.presto.app.user.web;

import com.presto.api.user.service.AccountService;
import com.presto.api.user.vo.RechargeVO;
import com.presto.common.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shihao on 16/12/12.
 */
@RestController
@RequestMapping("/recharge")
public class RechargeController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/byItem",method = RequestMethod.POST)
    public JsonResult rechargeByItem(@RequestBody RechargeVO vo){
        if(vo.getId() == null){
            return JsonResult.failure("充值项不能为空");
        }
        if(vo.getUserId() == null){
            return JsonResult.failure("用户ID不能为空");
        }
        return JsonResult.success(accountService.recharge(vo));
    }

}
