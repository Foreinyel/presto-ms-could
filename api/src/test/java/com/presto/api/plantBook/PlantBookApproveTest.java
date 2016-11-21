package com.presto.api.plantBook;

import com.presto.api.plantBook.service.PlantBookReqService;
import com.presto.api.plantBook.vo.PlantBookReqVO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by shihao on 16/11/19.
 */
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
