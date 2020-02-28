package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseResourceArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午11:10
 */
@RestController
@RequestMapping("/course")
@Api(tags = "课程相关")
@Slf4j
public class CourseController extends BaseController{
    @Autowired(required = false)
    private CourseService courseService;

    @ApiOperation(value = "创建课程")
    @PostMapping("/createCourse")
    public Result<Void> createCourse(CreateCourseArg arg) {
        return courseService.createCourse(arg);
    }

    @ApiOperation(value = "删除课程")
    @PostMapping("/deleteByIds")
    public Result<Void> deleteByIds(List<String> ids) {
        return courseService.deleteByIds(ids);
    }

    @ApiOperation(value = "获取所有课程")
    @PostMapping("/listCourses")
    public Result<List<CourseResult>> listCourses() {
        return courseService.listCourses();
    }
}
