package com.github.jiangxch.courselearningmanagement.test.provider;

import com.github.jiangxch.courselearningmanagement.provider.CourseLearningManagementProviderApplication;
import com.github.jiangxch.courselearningmanagement.provider.dao.CourseEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseEntity;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseArg;
import com.github.jiangxch.courselearningmanagement.providerapi.service.CourseService;
import javafx.application.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: sanjin
 * @date: 2020/2/25 下午6:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseLearningManagementProviderApplication.class)
public class BaseTest {
    @Autowired(required = false)
    private CourseEntityDao courseEntityDao;

    @Test
    public void addTestData(){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId("1");
        courseEntity.setSchoolName("湖北工业大学");
        courseEntity.setAcademyName("电气与电子工程学院");
        courseEntity.setMajorName("通信工程");
        courseEntity.setCourseName("数字信号处理");
        courseEntity.setIsObligatory(0);
        courseEntityDao.save(courseEntity);
    }
}
