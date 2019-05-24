package com.github.oahnus.scaffold.common.query;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by oahnus on 2019/4/19
 * 16:30.
 */
public class QueryBuilder extends Example {
    private Criteria criteria;

    public QueryBuilder(Class<?> entityClass) {
        super(entityClass);
        this.criteria = this.createCriteria();
    }

    public QueryBuilder(Class<?> entityClass, boolean exists) {
        super(entityClass, exists);
        this.criteria = this.createCriteria();
    }

    public QueryBuilder(Class<?> entityClass, boolean exists, boolean notNull) {
        super(entityClass, exists, notNull);
        this.criteria = this.createCriteria();
    }

    public QueryBuilder eq(String property, Object val){
        criteria.andEqualTo(property, val);
        return this;
    }

    public QueryBuilder notEq(String property, Object val) {
        criteria.andNotEqualTo(property, val);
        return this;
    }

    public QueryBuilder like(String property, String val) {
        criteria.andLike(property, "%" + val + "%");
        return this;
    }

    public QueryBuilder likeRight(String property, String val) {
        criteria.andLike(property, val + "%");
        return this;
    }

    public QueryBuilder in(String property, Iterable listVal) {
        criteria.andIn(property, listVal);
        return this;
    }
}
