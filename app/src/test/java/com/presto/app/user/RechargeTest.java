package com.presto.app.user;

import com.presto.api.user.service.AccountService;
import com.presto.api.user.vo.RechargeVO;
import com.presto.appApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shihao on 16/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = appApplication.class)
public class RechargeTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void recharge() {
        RechargeVO vo = new RechargeVO();
        vo.setId(5L);
        vo.setUserId(1L);
        accountService.recharge(vo);
    }

}
