package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.args.PageArg;
import com.github.jiangxch.courselearningmanagement.common.args.SearchArg;
import com.github.jiangxch.courselearningmanagement.common.result.PageResult;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResourceResult;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseResourceArg;
import com.github.jiangxch.courselearningmanagement.providerapi.service.CourseResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午9:31
 */
@RestController
@RequestMapping("/courseResource")
@Api(tags = "课程资源相关")
public class CourseResourceController extends BaseController{
    @Autowired(required = false)
    private CourseResourceService courseResourceService;

    @ApiOperation(value = "上传课程资源")
    @PostMapping(value = "/createCourseResource")
    public Result<Void> createCourseResource(CreateCourseResourceArg arg,
                                             HttpServletRequest request) {

        return courseResourceService.createCourseResource(arg, getUserId());
    }

    @ApiOperation(value = "删除课程资源")
    @PostMapping("/deleteByIds")
    public Result<Void> deleteByIds(@RequestBody List<String> ids) {

        return courseResourceService.deleteByIds(ids);
    }

    @ApiOperation(value = "通过id查询课程资源")
    @PostMapping("/getCourseResourceById")
    public Result<CourseResourceResult> getCourseResourceById(@RequestParam(required = true) String id) {

        return courseResourceService.getCourseResourceById(id);
    }

    @ApiOperation(value = "分页关键字搜索课程资源")
    @PostMapping("/searchCourseResourcesByKeyword")
    public Result<PageResult<CourseResourceResult>> searchCourseResourcesByKeyword(SearchArg searchArg) {
        return courseResourceService.listCourseResourcesByPage(searchArg);
    }
}
