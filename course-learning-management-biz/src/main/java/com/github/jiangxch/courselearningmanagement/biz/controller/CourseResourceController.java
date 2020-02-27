package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.args.PageArg;
import com.github.jiangxch.courselearningmanagement.common.result.PageResult;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResourceResult;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午9:31
 */
@RestController
@RequestMapping("/courseResource")
@Api(tags = "课程资源相关")
public class CourseResourceController extends BaseController{

    @ApiOperation(value = "上传课程资源")
    @PostMapping("/createCourseResource")
    public Result<Void> createCourseResource(CreateCourseResource arg) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "删除课程资源")
    @PostMapping("/deleteByIds")
    public Result<Void> deleteByIds(List<Integer> ids) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "通过id查询课程资源")
    @PostMapping("/getCourseResourceById")
    public Result<CourseResourceResult> getCourseResourceById(@RequestParam(required = true) Integer id) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "分页查询课程资源")
    @PostMapping("/listCourseResourcesByPage")
    public Result<PageResult<CourseResourceResult>> listCourseResourcesByPage(PageArg pageArg) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "分页关键字搜索课程资源")
    @PostMapping("/searchCourseResourcesByKeyword")
    public Result<PageResult<CourseResourceResult>> searchCourseResourcesByKeyword(PageArg pageArg) {
        return Result.newSuccess();
    }
}
