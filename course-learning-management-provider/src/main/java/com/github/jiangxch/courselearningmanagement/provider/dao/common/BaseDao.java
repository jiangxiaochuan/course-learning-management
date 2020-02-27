package com.github.jiangxch.courselearningmanagement.provider.dao.common;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.InsertOptions;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDao<T> implements InitializingBean {
    protected Class<T> entityClazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    @Autowired
    protected Datastore datastore;

    public Query<T> createQuery() {
        return datastore.createQuery(entityClazz);
    }

    public Query<T> createQuery(final String key, final Object value) {
        Query<T> query = createQuery();
        query.field(key).equal(value);
        return query;
    }

    public Query<T> createQuery(final List<String> keys, final List<Object> values) {
        Query<T> query = createQuery();
        if (keys.size() != values.size()) {
            return null;
        }

        for (int i = 0; i < keys.size(); i++) {
            query.field(keys.get(i)).equal(values.get(i));
        }

        return query;
    }

    public UpdateOperations<T> createUpdate() {
        return datastore.createUpdateOperations(entityClazz);
    }

    public T findOne(final String key, final Object value) {
        return datastore.find(entityClazz, key, value).get();
    }

    public T findOne(final Map<String, Object> conditions) {
        Query<T> query = createQuery();
        conditions.forEach((key, value) -> query.field(key).equal(value));
        return query.get();
    }

    public List<T> find(final String key, final Object value) {
        return datastore.find(entityClazz, key, value).asList();
    }

    public List<T> find(final Map<String, Object> conditions) {
        Query<T> query = createQuery();
        conditions.forEach((key, value) -> query.field(key).equal(value));
        return query.asList();
    }

    public List<T> find(final List<String> keys, final List<Object> values) {
        return createQuery(keys, values).asList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        datastore.ensureIndexes(entityClazz, true);
    }
}
