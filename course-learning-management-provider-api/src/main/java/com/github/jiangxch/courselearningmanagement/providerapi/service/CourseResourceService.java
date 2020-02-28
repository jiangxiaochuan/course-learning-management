package com.github.jiangxch.courselearningmanagement.providerapi.service;


import com.github.jiangxch.courselearningmanagement.common.args.PageArg;
import com.github.jiangxch.courselearningmanagement.common.args.SearchArg;
import com.github.jiangxch.courselearningmanagement.common.result.PageResult;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseResourceArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResourceResult;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午2:53
 */
public interface CourseResourceService {

    Result<Void> createCourseResource(CreateCourseResourceArg arg, String userId);

    Result<Void> deleteByIds(List<String> ids);

    Result<CourseResourceResult> getCourseResourceById(String id);

    Result<PageResult<CourseResourceResult>> listCourseResourcesByPage(SearchArg searchArg);
}
