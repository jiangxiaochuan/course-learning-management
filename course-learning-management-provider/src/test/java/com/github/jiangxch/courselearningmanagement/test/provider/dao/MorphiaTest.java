package com.github.jiangxch.courselearningmanagement.test.provider.dao;

import com.github.jiangxch.courselearningmanagement.common.utils.DateUtil;
import com.github.jiangxch.courselearningmanagement.provider.dao.UserEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.UserEntity;
import com.github.jiangxch.courselearningmanagement.test.provider.dao.entity.PersonEntity;
import com.github.jiangxch.courselearningmanagement.test.provider.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * 测试 Morphia 是否正常
 * @author: sanjin
 * @date: 2020/2/25 下午6:20
 */
public class MorphiaTest extends BaseTest {
    @Autowired(required = false)
    private Datastore datastore;
    @Autowired
    private UserEntityDao userEntityDao;

    @Test
    public void testCURD() {
        testInsert();
        testQuery();
        testUpdate();
        testDelete();


    }

    public void testInsert() {
        System.out.println("测试 insert start");

        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("小明");
        personEntity.setGenre("难");
        personEntity.setAge(11);
        personEntity.setSalary(11.11);

        datastore.save(personEntity);
        System.out.println("测试 insert end");

    }

    public void testQuery() {
        System.out.println("测试 query start");

        Query<PersonEntity> query = datastore.createQuery(PersonEntity.class);
        List<PersonEntity> personEntities = query.asList();
        System.out.println(personEntities);
        System.out.println("测试 query end");
        Assert.assertEquals(personEntities.size(),1);

    }

    public void testUpdate() {

        PersonEntity beforeUpdate = null;
        PersonEntity afterUpdate = null;

        System.out.println("测试update start");

        Query<PersonEntity> query = datastore.createQuery(PersonEntity.class);
        System.out.println("跟新之前: ");
        System.out.println(query.asList());
        beforeUpdate = query.asList().get(0);
        UpdateOperations<PersonEntity> updateOperations = datastore.createUpdateOperations(PersonEntity.class);
        // 将薪水salary字段增加1000
        updateOperations.inc("salary", 1000);
        datastore.update(query,updateOperations);
        System.out.println("跟新之后:");
        Query<PersonEntity> query2 = datastore.createQuery(PersonEntity.class);
        System.out.println(query2.asList());
        System.out.println("测试update end");
        afterUpdate = query2.asList().get(0);
        Assert.assertEquals(beforeUpdate.getSalary() + 1000, afterUpdate.getSalary(), 1);
    }

    public void testDelete() {
        System.out.println("测试 delete start");

        Query<PersonEntity> query = datastore.createQuery(PersonEntity.class);
        System.out.println("delete之前: ");
        System.out.println(query.asList());
        Assert.assertTrue(query.asList().size() > 0);
        datastore.delete(query);

        Query<PersonEntity> query2 = datastore.createQuery(PersonEntity.class);
        System.out.println(query2.asList());
        System.out.println("测试 delete end");
        Assert.assertTrue(query2.asList().size() == 0);
    }


    @Test
    public void insertOne() {
        UserEntity user = userEntityDao.findOne("id", "1");
        if (user == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId("1");
            userEntity.setOpenId("xxxxx");
            userEntity.setNickname("三斤");
            userEntity.setProfile("https://wwww.baidu,com/1.jpg");
            userEntity.setUsername("admin");
            userEntity.setPassword("admin");
            userEntity.setRoleType(1);
            userEntity.setCreateTime(DateUtil.getUnix());
            userEntity.setUpdateTime(DateUtil.getUnix());
            userEntityDao.save(userEntity);
        }
    }

}
