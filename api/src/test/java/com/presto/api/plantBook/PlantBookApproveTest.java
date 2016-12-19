package com.presto.api.plantBook;

import com.presto.api.plantBook.service.PlantBookReqService;
import com.presto.api.plantBook.vo.PlantBookReqVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by shihao on 16/11/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringBootSampleApplication.class)
public class PlantBookApproveTest {

    @Resource
    PlantBookReqService plantBookReqService;

    @Test
    public void approveBookReq(){
        PlantBookReqVO vo = new PlantBookReqVO();
        vo.setId(3L);
        plantBookReqService.plantBookReqApproval(vo);
    }

}
