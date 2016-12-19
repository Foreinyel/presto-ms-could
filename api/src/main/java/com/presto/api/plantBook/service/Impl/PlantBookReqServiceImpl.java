package com.presto.api.plantBook.service.Impl;

import com.presto.api.book.dao.BookDao;
import com.presto.api.book.entity.Books;
import com.presto.api.plantBook.dao.PlantBookDao;
import com.presto.api.plantBook.dao.UserBookDao;
import com.presto.api.plantBook.entity.PlantBookReq;
import com.presto.api.plantBook.entity.UserBook;
import com.presto.api.plantBook.ro.PlantBookReqRO;
import com.presto.api.plantBook.service.PlantBookReqService;
import com.presto.api.plantBook.vo.PlantBookReqVO;
import com.presto.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by shihao on 16/11/2.
 */
@Service
public class PlantBookReqServiceImpl implements PlantBookReqService {

    @Autowired
    private PlantBookDao plantBookDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserBookDao userBookDao;

    public PlantBookReq savePlantBookReq(PlantBookReqVO vo) {
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

        //测试阶段直接通过
//        vo.setId(plantBookReq.getId());
//        this.plantBookReqApproval(vo);

        return plantBookReq;
    }

    public int updatePlantBookReq(PlantBookReqVO vo){
        PlantBookReq plantBookReq = plantBookDao.findById(PlantBookReq.class,vo.getId());
        plantBookReq.setUpdatedDate(new Date());
        plantBookReq.setBookPress(vo.getBookPress());
        plantBookReq.setBookAuthor(vo.getBookAuthor());
        plantBookReq.setBookImgUrl(vo.getBookImgUrl());
        plantBookReq.setBookIsbn(vo.getBookIsbn());
        plantBookReq.setBookName(vo.getBookName());
        plantBookDao.update(plantBookReq);
        return 0;
    }

    public PlantBookReq plantBookReqApproval(PlantBookReqVO vo) {
        PlantBookReq plantBookReq = plantBookDao.findById(PlantBookReq.class, vo.getId());
        Long bookId;
        //1.如果是手动植书,需要先将书籍信息创建到书库表里,再和用户建立关联
        if (plantBookReq.getPlantMethod() == CommonConstants.PlantBookMethod.MANUAL) {
            Books books = new Books();
            books.setBookPress(plantBookReq.getBookPress());
            books.setBookAuthor(plantBookReq.getBookAuthor());
            books.setBookName(plantBookReq.getBookName());
            books.setBookIsbn(plantBookReq.getBookIsbn());
            books.setBookImgUrl(plantBookReq.getBookImgUrl());
            bookDao.insert(books);


            bookId = books.getId();
        }else{  //非手动植书,书已在库中

            bookId = plantBookReq.getBookId();
        }

        UserBook userBook = new UserBook();
        userBook.setCreatedDate(new Date());
        userBook.setBookId(bookId);
        userBook.setUserId(plantBookReq.getUserId());
        userBook.setUpdatedDate(new Date());

        userBookDao.insert(userBook);

        plantBookReq.setStatus(CommonConstants.PlantBookReqStatus.APPROVE);
        plantBookDao.update(plantBookReq);

        return plantBookReq;

    }

    public PlantBookReq plantBookReqReject(PlantBookReqVO vo){
        PlantBookReq plantBookReq = plantBookDao.findById(PlantBookReq.class, vo.getId());
        plantBookReq.setStatus(CommonConstants.PlantBookReqStatus.REJECT);
        plantBookDao.update(plantBookReq);
        return plantBookReq;
    }

    public List<PlantBookReqRO> findAllNewReqs(){
        return plantBookDao.findAllNewReqs();
    }

}
