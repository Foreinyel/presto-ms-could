package com.presto.common.dao;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;

import java.util.List;

/**
 * Created by shihao on 16/10/29.
 */
public class PageInfo<E> {

    private List<E> results;

    private Paginator paginator;

    public PageInfo() {
    }

    public PageInfo(List<E> r) {
        this.setResults(r);
    }

    public PageInfo(List<E> r, Paginator p) {
        this.setResults(r);
        this.setPaginator(p);
    }

    public List<E> getResults() {
        return results;
    }

    public void setResults(List<E> results) {
        this.results = results;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
