package com.github.jiangxch.courselearningmanagement.providerapi.service;


import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResult;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午2:53
 */
public interface CourseService{
    Result<Void> createCourse(CreateCourseArg arg);

    Result<Void> deleteByIds(List<String> ids);

    Result<List<CourseResult>> listCourses();

    Result<CourseResult> getCourseResultById(String courseId);
}
