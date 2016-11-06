package com.presto.api.plantBook.service.Impl;

import com.presto.api.plantBook.dao.PlantBookDao;
import com.presto.api.plantBook.entity.PlantBookReq;
import com.presto.api.plantBook.service.PlantBookReqService;
import com.presto.api.plantBook.vo.PlantBookReqVO;
import com.presto.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shihao on 16/11/2.
 */
@Service
public class PlantBookReqServiceImpl implements PlantBookReqService {

    @Autowired
    private PlantBookDao plantBookDao;

    public PlantBookReq savePlantBookReq(PlantBookReqVO vo){
        PlantBookReq plantBookReq = new PlantBookReq();
        plantBookReq.setDeletedFlag(0);
        plantBookReq.setCreatedDate(new Date());
        plantBookReq.setUpdatedDate(new Date());
        plantBookReq.setStatus(CommonConstants.PlantBookReqStatus.NEW);

        plantBookReq.setBookAuthor(vo.getBookAuthor());
        plantBookReq.setBookId(vo.getBookId());
        plantBookReq.setBookImgUrl(vo.getBookImgUrl());
        plantBookReq.setBookIsbn(vo.getBookIsbn());
        plantBookReq.setBookName(vo.getBookName());
        plantBookReq.setBookPress(vo.getBookPress());
        plantBookReq.setUserId(vo.getUserId());
        plantBookReq.setPlantType(vo.getPlantType());
        plantBookReq.setPlantMethod(vo.getPlantMethod());

        plantBookDao.insert(plantBookReq);

        return plantBookReq;
    }

}
