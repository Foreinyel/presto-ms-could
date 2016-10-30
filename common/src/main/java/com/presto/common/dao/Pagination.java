package com.presto.common.dao;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.presto.common.exception.CommonException;
import com.presto.common.exception.CommonExceptionCode;
import com.presto.common.utils.ObjectUtil;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * Created by shihao on 16/10/29.
 */
public class Pagination<T extends Serializable> extends PageBounds {
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_LIMIT = 20;

    private List<T> results = new ArrayList<T>();

    private T cond;

    private Map<String, Object> condMap;

    @JSONField(serialize = false, deserialize = false)
    private Class<T> entityClass;

    private Paginator paginator = new Paginator(1, 10, 0);

    @JSONField(serialize = false)
    protected int page = NO_PAGE;

    @JSONField(serialize = false)
    protected int limit = NO_ROW_LIMIT;

    @JSONField(serialize = false)
    protected List<Order> orders = new ArrayList<Order>();

    @JSONField(serialize = false)
    protected boolean containsTotalCount;

    @JSONField(serialize = false)
    protected Boolean asyncTotalCount;

    public Pagination() {
        super(NO_PAGE, DEFAULT_LIMIT);
    }

    public Pagination(Class<T> entityClass, Map<String, Object> condMap) {
        super(PageBounds.NO_PAGE, DEFAULT_LIMIT);
        this.condMap = condMap;
        this.entityClass = entityClass;
    }

    public Pagination(Class<T> entityClass, Map<String, Object> condMap, int page, int limit, Order... orders) {
        super(page, limit, orders);
        this.condMap = condMap;
        this.entityClass = entityClass;
    }

    public Pagination(int page, int limit, Order... orders) {
        super(page, limit, orders);
    }

    public Pagination(T cond) {
        super(NO_PAGE, DEFAULT_LIMIT);
        this.cond = cond;
    }

    public Pagination(T cond, int page, int limit, Order... orders) {
        super(page, limit, orders);
        this.cond = cond;
    }

    public <RO extends Serializable> Pagination<RO> convertToRO(Class<RO> roClazz) {
        Pagination<RO> page = new Pagination<RO>();
        page.page = this.page;
        page.setPaginator(this.getPaginator());
        page.limit = this.getPaginator().getLimit();
        page.setResults(ObjectUtil.convertList(this.getResults(), roClazz, null));
        return page;
    }

    public Pagination<T> resort(Comparator<T> comparator) {
        results.sort(comparator);
        return this;
    }

    public Pagination<T> customer(Function<T, T> func) {
        results.forEach(item -> {
            item = func.apply(item);
        });
        return this;
    }

    @JSONField(serialize = false, deserialize = false)
    public Class<?> getCondClass() {
        if (this.entityClass != null)
            return entityClass;
        if (this.cond != null)
            return cond.getClass();
        throw new CommonException(CommonExceptionCode.E999999);
    }

    /**
     * map is first
     *
     * @return
     */
    @JSONField(serialize = false, deserialize = false)
    public Map<String, Object> getCondMap() {
        if (condMap != null)
            return condMap;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cond", cond);
        return paramMap;
    }

    @JSONField(serialize = false)
    public int getOffset() {
        if (page >= 1) {
            return (page - 1) * limit;
        }
        return 0;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public List<T> getResults() {
        return results;
    }

    public Pagination<T> setPageResults(List<T> results) {
        this.results = results;
        paginator = ((PageList<T>) results).getPaginator();
        return this;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return super.toString() + "    results:" + results.size();
    }
}
