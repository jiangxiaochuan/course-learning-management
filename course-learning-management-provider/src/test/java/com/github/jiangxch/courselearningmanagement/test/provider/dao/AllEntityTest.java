package com.github.jiangxch.courselearningmanagement.test.provider.dao;

import com.github.jiangxch.courselearningmanagement.provider.dao.CourseResourceEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.dao.UserEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseResourceEntity;
import com.github.jiangxch.courselearningmanagement.provider.entity.UserEntity;
import com.github.jiangxch.courselearningmanagement.test.provider.BaseTest;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sanjin
 * @date: 2020/2/29 下午7:49
 */
public class AllEntityTest extends BaseTest {
    @Autowired
    private CourseResourceEntityDao courseResourceEntityDao;
    @Autowired
    private UserEntityDao userEntityDao;
    @Autowired
    private Datastore datastore;

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


    @Test
    public void userTest() {
        UserEntity user = userEntityDao.findOne("id", "1");
        UpdateOperations<UserEntity> updateOperations = userEntityDao.createUpdate();
        updateOperations.set("profile","https://upload-images.jianshu.io/upload_images/12666922-578042f9777db600.jpg");
        datastore.update(user,updateOperations);
    }


}
