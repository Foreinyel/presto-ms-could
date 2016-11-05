package com.presto.api.plantBook.service;

import com.presto.api.plantBook.entity.PlantBookReq;
import com.presto.api.plantBook.vo.PlantBookReqVO;

/**
 * Created by shihao on 16/11/2.
 */
public interface PlantBookReqService {

    PlantBookReq savePlantBookReq(PlantBookReqVO vo);
}
