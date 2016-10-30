package com.presto.api.user.service;

import com.presto.api.user.entity.InviteCode;

/**
 * Created by shihao on 16/10/30.
 */
public interface InviteCodeService {
    InviteCode findByCode(final String code);
}
