package com.presto.common.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by shihao on 16/10/29.
 */
public interface CommonDao {

    <T extends BaseEntity, ID extends Serializable> T findById(Class<T> clazz, ID id);

    <T extends BaseEntity, ID extends Serializable> T findByIdWithLock(Class<T> clazz, ID id);

    <T extends BaseEntity, ID extends Serializable> int removeById(Class<T> clazz, ID id);

    <T extends BaseEntity, ID extends Serializable> int removeByIdCompletely(Class<T> clazz, ID id);

    <T extends BaseEntity> int removeOnCondition(T entity);

    <T extends BaseEntity> int removeOnConditionCompletely(T entity);

    <T extends BaseEntity> int insert(T entity);

    <T extends BaseEntity> int insertSelective(T entity);

    <T extends BaseEntity> int insertWithId(T entity);

    <T extends BaseEntity> int batchInsert(List<T> entities);

    <T extends BaseEntity> int batchInsertWithId(List<T> entities);

    <T extends BaseEntity> int update(T entity);

    <T extends BaseEntity> int updateSelective(T entity);

    <T extends BaseEntity> Long countOnCondition(T entity);

    <T extends BaseEntity> BigDecimal sumOnCondition(T entity, String col);

    <T extends BaseEntity> T searchOne(T entity);

    <T extends BaseEntity> List<T> search(T entity);

    <T extends BaseEntity> List<T> search(Class<T> entityClass, Map<String, Object> condMap);

    <T extends BaseEntity> Pagination<T> searchByPage(Pagination<T> page);

    <T extends BaseEntity> PageInfo<T> search(T entity, PageBounds pageBounds);

    SqlSession getSession();
}
