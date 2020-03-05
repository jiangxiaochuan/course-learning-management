package com.github.jiangxch.courselearningmanagement.test.provider.dao;

import com.github.jiangxch.courselearningmanagement.provider.dao.CourseResourceEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseResourceEntity;
import com.github.jiangxch.courselearningmanagement.test.provider.BaseTest;
import org.junit.Test;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sanjin
 * @date: 2020/2/29 下午7:49
 */
public class MongoDatabaseOp extends BaseTest {
    @Autowired
    private CourseResourceEntityDao courseResourceEntityDao;

    @Test
    public void delete() {
    }

    @Test
    public void testLikeQuery() {
        Query<CourseResourceEntity> query = courseResourceEntityDao.createQuery();
        System.out.println("全部查询");
        System.out.println(query.asList());
        System.out.println("模糊查询");
        query.criteria("resourceName").containsIgnoreCase("啊");
        System.out.println(query.asList());
    }


}
