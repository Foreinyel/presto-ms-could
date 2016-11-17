package com.presto.api.order.dao;

import com.presto.api.order.ro.OrderRO;
import com.presto.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * Created by shihao on 16/11/15.
 */
public interface OrderDao extends CommonDao {

    List<OrderRO> queryOrdersByUser(Map<String,Object> param);
}
