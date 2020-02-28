package com.github.jiangxch.courselearningmanagement.provider.service;

import com.github.jiangxch.courselearningmanagement.common.args.PageArg;
import com.github.jiangxch.courselearningmanagement.common.args.SearchArg;
import com.github.jiangxch.courselearningmanagement.common.result.PageResult;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.common.utils.IdUtil;
import com.github.jiangxch.courselearningmanagement.oos.result.FileResult;
import com.github.jiangxch.courselearningmanagement.oos.service.Oos;
import com.github.jiangxch.courselearningmanagement.provider.dao.CourseResourceEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseEntity;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseResourceEntity;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseResourceArg;
import com.github.jiangxch.courselearningmanagement.providerapi.enums.ResourceTypeEnum;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResourceResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.CourseResourceService;
import com.github.jiangxch.courselearningmanagement.providerapi.service.CourseService;
import com.github.jiangxch.courselearningmanagement.providerapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/28 上午12:12
 */
@Service
public class CourseResourceServiceImpl implements CourseResourceService {
    @Autowired
    private CourseResourceEntityDao courseResourceEntityDao;
    @Autowired(required = false)
    private Oos oos;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @Override
    public Result<Void> createCourseResource(CreateCourseResourceArg arg, String userId) {
        Result<FileResult> uploadResult = oos.upload(arg.getFile());
        if (!uploadResult.hasSuccess()) {
            return Result.newError("文件上传到Oos失败,文件名称:" + oos.getFileName(arg.getFile()));
        }
        FileResult fileResult = uploadResult.getData();
        CourseResourceEntity courseResourceEntity = new CourseResourceEntity();
        courseResourceEntity.setUrl(fileResult.getUrl());
        courseResourceEntity.setId(IdUtil.generateId());
        courseResourceEntity.setResourceName(arg.getResourceName());
        courseResourceEntity.setResourceType(arg.getResourceType());
        courseResourceEntity.setSchoolName(arg.getSchoolName());
        courseResourceEntity.setUserId(userId);
        courseResourceEntity.setExt(fileResult.getExt());
        courseResourceEntity.setCourseId(arg.getCourseId());
        courseResourceEntity.setCourseName(arg.getCourseName());
        courseResourceEntityDao.save(courseResourceEntity);
        return Result.newSuccess();
    }

    @Override
    public Result<Void> deleteByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            courseResourceEntityDao.deleteByIds(ids);
        }
        return Result.newSuccess();
    }

    @Override
    public Result<CourseResourceResult> getCourseResourceById(String id) {
        CourseResourceEntity courseResourceEntity = courseResourceEntityDao.findByPrimaryKey(id);
        CourseResourceResult result = entity2Result(courseResourceEntity);
        return Result.newSuccess(result);
    }

    @Override
    public Result<PageResult<CourseResourceResult>> listCourseResourcesByPage(SearchArg searchArg) {
        PageResult<CourseResourceEntity> courseEntityPageResult =
                courseResourceEntityDao.listCourseResourcesByPage(searchArg);
        PageResult<CourseResourceResult> result = new PageResult<>();
        BeanUtils.copyProperties(courseEntityPageResult,result);
        result.setRows(entity2Result(courseEntityPageResult.getRows()));
        return Result.newSuccess(result);
    }

    private CourseResourceResult entity2Result(CourseResourceEntity entity) {
        CourseResourceResult result = new CourseResourceResult();
        BeanUtils.copyProperties(entity,result);
        result.setResourceTypeMsg(ResourceTypeEnum.getByType(entity.getResourceType()).getMsg());
        result.setUserInfoResult(userService.getUserResultById(entity.getUserId()).getData());
        result.setCourseResult(courseService.getCourseResultById(entity.getCourseId()).getData());
        return result;
    }

    private List<CourseResourceResult> entity2Result(List<CourseResourceEntity> entityList) {
        List<CourseResourceResult> result = new ArrayList<>();
        for (CourseResourceEntity courseResourceEntity : entityList) {
            CourseResourceResult courseResourceResult = entity2Result(courseResourceEntity);
            result.add(courseResourceResult);
        }
        return result;
    }
}
