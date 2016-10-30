package com.presto.app.user.web;

import com.presto.api.user.entity.User;
import com.presto.api.user.service.InviteCodeService;
import com.presto.api.user.service.UserService;
import com.presto.api.user.vo.UserVo;
import com.presto.common.result.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by shihao on 16/10/29.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private InviteCodeService inviteCodeService;

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public JsonResult findUserById(){
        return JsonResult.success(userService.findById(1L));
    }

    @RequestMapping(value = "/findByMobile",method = RequestMethod.GET)
    public JsonResult findUserByMobile(@Valid @RequestParam String mobile){
        User user = userService.findByMobile(mobile);
        if(user != null){
            return JsonResult.success("该用户已注册");
        }else{
            return JsonResult.failure("新用户未注册");
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult login(@Valid @RequestBody UserVo vo){
        if(StringUtils.isBlank(vo.getMobile())){
            return JsonResult.failure("手机号不能为空");
        }
        if(StringUtils.isBlank(vo.getPasswd())){
            return JsonResult.failure("验证码不能为空");
        }

        //1.是否新用户
        User user = userService.findByMobile(vo.getMobile());
        if(user == null){   //新用户走注册流程
            if(StringUtils.isBlank(vo.getInviteCode())){
                return JsonResult.failure("邀请码不能为空");
            }
            if(null == inviteCodeService.findByCode(vo.getInviteCode())){
                return JsonResult.failure("无效的邀请码");
            }
            return JsonResult.success(userService.register(vo));
        }else{  //老用户登录
            user = userService.login(vo);
            if(user == null){
                return JsonResult.failure("登录失败,检查账户密码是否正确");
            }
            return JsonResult.success(user);
        }
    }

}
