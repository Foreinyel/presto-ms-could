package com.presto.common.dao;

import java.io.Serializable;

/**
 * Created by shihao on 16/10/29.
 */
public interface Entity<ID extends Serializable> {
    public ID getId();

    public void setId(ID id);
}
