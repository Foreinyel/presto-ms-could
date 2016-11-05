package com.presto.api.plantBook.dao.Impl;

import com.presto.api.plantBook.dao.PlantBookDao;
import com.presto.api.plantBook.entity.PlantBookReq;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by shihao on 16/11/2.
 */
@Repository
public class PlantBookDaoImpl extends CommonDaoImpl implements PlantBookDao {

    private static final String NAMESPACE = PlantBookReq.class.getName() + ".";

}
