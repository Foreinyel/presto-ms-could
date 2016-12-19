package com.presto.api.plantBook.dao.Impl;

import com.presto.api.plantBook.dao.PlantBookDao;
import com.presto.api.plantBook.entity.PlantBookReq;
import com.presto.api.plantBook.ro.PlantBookReqRO;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shihao on 16/11/2.
 */
@Repository
public class PlantBookDaoImpl extends CommonDaoImpl implements PlantBookDao {

    private static final String NAMESPACE = PlantBookReq.class.getName() + ".";

    public List<PlantBookReqRO> findAllNewReqs() {
        return getSqlSession().selectList(NAMESPACE + "findAllNewReqs");
    }

}
