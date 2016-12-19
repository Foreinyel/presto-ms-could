package com.presto.api.plantBook.service;

import com.presto.api.plantBook.entity.PlantBookReq;
import com.presto.api.plantBook.ro.PlantBookReqRO;
import com.presto.api.plantBook.vo.PlantBookReqVO;

import java.util.List;

/**
 * Created by shihao on 16/11/2.
 */
public interface PlantBookReqService {

    PlantBookReq savePlantBookReq(PlantBookReqVO vo);
    int updatePlantBookReq(PlantBookReqVO vo);
    PlantBookReq plantBookReqApproval(PlantBookReqVO vo);
    PlantBookReq plantBookReqReject(PlantBookReqVO vo);
    List<PlantBookReqRO> findAllNewReqs();
}
