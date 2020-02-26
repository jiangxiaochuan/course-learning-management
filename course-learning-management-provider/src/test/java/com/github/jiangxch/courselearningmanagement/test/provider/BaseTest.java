package com.github.jiangxch.courselearningmanagement.test.provider;

import com.github.jiangxch.courselearningmanagement.provider.CourseLearningManagementProviderApplication;
import javafx.application.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: sanjin
 * @date: 2020/2/25 下午6:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseLearningManagementProviderApplication.class)
public class BaseTest {
    @Test
    public void test(){

    }
}
