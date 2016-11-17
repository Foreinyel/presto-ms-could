package com.presto.api.order.dao.Impl;

import com.presto.api.order.dao.OrderDao;
import com.presto.api.order.entity.Order;
import com.presto.api.order.ro.OrderRO;
import com.presto.common.dao.CommonDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by shihao on 16/11/15.
 */
@Repository
public class OrderDaoImpl extends CommonDaoImpl implements OrderDao {

    private static final String NAMESPACE = Order.class.getName() + ".";

    public List<OrderRO> queryOrdersByUser(Map<String,Object> param){
        return getSqlSession().selectList(NAMESPACE + "queryOrderByUser",param);
    }

}
