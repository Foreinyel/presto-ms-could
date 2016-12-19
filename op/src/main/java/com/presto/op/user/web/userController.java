package com.presto.op.user.web;

import com.presto.api.opUser.ro.OpUserRO;
import com.presto.api.opUser.vo.OpUserVO;
import com.presto.common.result.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shihao on 16/12/12.
 */
@RestController
@RequestMapping("/user")
public class userController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult userLogin(@RequestBody OpUserVO vo) {
        if (vo.getMobile().equals("15026801649") && vo.getPassword().equals("000000")) {
            OpUserRO opUserRO = new OpUserRO();
            opUserRO.setId(1L);
            opUserRO.setMobile(vo.getMobile());
            return JsonResult.success(opUserRO);
        }
        return JsonResult.failure("账号密码不正确");
    }

}
