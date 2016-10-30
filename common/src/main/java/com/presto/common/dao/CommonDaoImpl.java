package com.presto.common.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.presto.common.exception.CommonException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shihao on 16/10/29.
 */
public class CommonDaoImpl extends SqlSessionDaoSupport implements CommonDao {
    public static final String FAIL_OPERATION = "操作失败";

    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public SqlSession getSession() {
        return getSqlSession();
    }

    @Override
    public <T extends BaseEntity, ID extends Serializable> T findById(Class<T> clazz, ID id) {
        if (id == null || clazz == null)
            return null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("lock", false);
        return getSqlSession().selectOne(clazz.getName() + ".select", params);
    }

    @Override
    public <T extends BaseEntity, ID extends Serializable> T findByIdWithLock(Class<T> clazz, ID id) {
        if (id == null || clazz == null)
            return null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("lock", true);
        return getSqlSession().selectOne(clazz.getName() + ".select", params);
    }

    @Override
    @Transactional
    public <T extends BaseEntity, ID extends Serializable> int removeById(Class<T> clazz, ID id) {
        if (id == null || clazz == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().delete(clazz.getName() + ".deleteById", id);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int removeOnCondition(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cond", entity);
        return getSqlSession().delete(entity.getClass().getName() + ".delete", params);
    }

    @Override
    @Transactional
    public <T extends BaseEntity, ID extends Serializable> int removeByIdCompletely(Class<T> clazz, ID id) {
        if (id == null || clazz == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().delete(clazz.getName() + ".deleteByIdCompletely", id);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int removeOnConditionCompletely(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cond", entity);
        return getSqlSession().delete(entity.getClass().getName() + ".deleteCompletely", params);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int insert(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().insert(entity.getClass().getName() + ".insert", entity);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int insertSelective(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().insert(entity.getClass().getName() + ".insertSelective", entity);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int insertWithId(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().insert(entity.getClass().getName() + ".insertWithId", entity);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int batchInsert(List<T> entities) {
        if (entities == null || entities.size() == 0)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().insert(entities.get(0).getClass().getName() + ".batchInsert", entities);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int batchInsertWithId(List<T> entities) {
        if (entities == null || entities.size() == 0)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().insert(entities.get(0).getClass().getName() + ".batchInsertWithId", entities);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int update(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().update(entity.getClass().getName() + ".update", entity);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> int updateSelective(T entity) {
        if (entity == null)
            throw new CommonException(FAIL_OPERATION);
        return getSqlSession().update(entity.getClass().getName() + ".updateSelective", entity);
    }

    @Override
    public <T extends BaseEntity> Long countOnCondition(T entity) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cond", entity);
        return getSqlSession().selectOne(entity.getClass().getName() + ".count", params);
    }

    @Override
    public <T extends BaseEntity> BigDecimal sumOnCondition(T entity, String col) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cond", entity);
        params.put("col", col);
        return getSqlSession().selectOne(entity.getClass().getName() + ".sum", params);
    }

    @Override
    public <T extends BaseEntity> List<T> search(T entity) {
        if (entity == null)
            return new ArrayList<T>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cond", entity);
        return getSqlSession().selectList(entity.getClass().getName() + ".query", params);
    }

    @Override
    public <T extends BaseEntity> T searchOne(T entity) {
        if (entity == null)
            return null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cond", entity);
        return getSqlSession().selectOne(entity.getClass().getName() + ".query", params);
    }

    @Override
    public <T extends BaseEntity> List<T> search(Class<T> entityClass, Map<String, Object> condMap) {
        if (entityClass == null)
            return new ArrayList<T>();
        Map<String, Object> params = new HashMap<String, Object>();
        if (condMap != null) {
            try {
                T instance = entityClass.newInstance();
                condMap.entrySet().forEach(entry -> {
                    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(entityClass, entry.getKey());
                    if (pd != null)
                        try {
                            pd.getWriteMethod().invoke(instance, entry.getValue());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                });
                params.put("cond", instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getSqlSession().selectList(entityClass.getName() + ".query", params);
    }

    @Override
    public <T extends BaseEntity> Pagination<T> searchByPage(Pagination<T> page) {
        if (page == null)
            return new Pagination<T>();
        return page.setPageResults(getSqlSession().selectList(page.getCondClass().getName() + ".query", page.getCondMap(), page));
    }

    @Override
    public <T extends BaseEntity> PageInfo<T> search(T entity, PageBounds pageBounds) {
        pageBounds.setContainsTotalCount(false);
        Map<String, Object> params = new HashMap<>();
        params.put("cond", entity);
        List<T> list = getSqlSession().selectList(entity.getClass().getName() + ".query", params, pageBounds);
        return new PageInfo<T>(list, new Paginator(pageBounds.getPage(), pageBounds.getLimit(), countOnCondition(entity).intValue()));
    }
}
