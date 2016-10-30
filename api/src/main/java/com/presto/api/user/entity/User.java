package com.presto.api.user.entity;

import com.presto.common.dao.BaseEntity;

/**
 * 用户表
 *
 * Auto Generated by <code>CodeGenerator</code>
 */
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String mobile;//手机号码
    private Long inviteCodeId;//注册邀请码
    private String passwd;//登录密码(验证码)

    /**
     * return 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * return 注册邀请码
     */
    public Long getInviteCodeId() {
        return inviteCodeId;
    }

    /**
     * @param inviteCodeId 注册邀请码
     */
    public void setInviteCodeId(Long inviteCodeId) {
        this.inviteCodeId = inviteCodeId;
    }

    /**
     * return 登录密码(验证码)
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd 登录密码(验证码)
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}