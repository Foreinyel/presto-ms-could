package com.presto.api.order.service;

import com.presto.api.order.ro.ContinueOrderItemRO;
import com.presto.api.order.vo.ContinueOrderVO;

import java.util.List;

/**
 * Created by shihao on 16/12/7.
 */
public interface ContinueOrderService {

    List<ContinueOrderItemRO> loadContinueOrderItems(ContinueOrderVO vo);

    int continueOrder(ContinueOrderVO vo);
}
