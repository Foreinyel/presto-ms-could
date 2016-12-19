package com.presto.api.plantBook.dao;

import com.presto.api.plantBook.ro.PlantBookReqRO;
import com.presto.common.dao.CommonDao;

import java.util.List;

/**
 * Created by shihao on 16/11/2.
 */
public interface PlantBookDao extends CommonDao {
    List<PlantBookReqRO> findAllNewReqs();
}
