package com.github.jiangxch.courselearningmanagement.all;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午3:58
 */
@SpringBootApplication(
        scanBasePackages = "com.github.jiangxch.courselearningmanagement"
)
@EnableSwagger2Doc
public class CourseLearningManagementAllApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseLearningManagementAllApplication.class, args);
    }
}
