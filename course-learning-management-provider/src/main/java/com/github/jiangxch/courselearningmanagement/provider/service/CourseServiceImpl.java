package com.github.jiangxch.courselearningmanagement.provider.service;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.result.Void;
import com.github.jiangxch.courselearningmanagement.common.utils.IdUtil;
import com.github.jiangxch.courselearningmanagement.provider.dao.CourseEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseEntity;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.CreateCourseArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.CourseResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Entity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午11:42
 */
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseEntityDao courseEntityDao;

    @Override
    public Result<Void> createCourse(CreateCourseArg arg) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(IdUtil.generateId());
        BeanUtils.copyProperties(arg,courseEntity);
        courseEntityDao.save(courseEntity);
        return Result.newSuccess();
    }

    @Override
    public Result<Void> deleteByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            courseEntityDao.deleteByIds(ids);
        }
        return Result.newSuccess();
    }

    @Override
    public Result<List<CourseResult>> listCourses() {
        List<CourseEntity> courseEntityList = courseEntityDao.findAll();
        List<CourseResult> result = courseEntityList.stream().map(x -> {
            CourseResult courseResult = new CourseResult();
            BeanUtils.copyProperties(x, courseResult);
            return courseResult;
        }).collect(Collectors.toList());
        return Result.newSuccess(result);
    }

    @Override
    public Result<CourseResult> getCourseResultById(String courseId) {
        CourseEntity courseEntity = courseEntityDao.findByPrimaryKey(courseId);
        if (courseEntity == null) {
            return Result.newSuccess();
        }
        CourseResult courseResult = new CourseResult();
        BeanUtils.copyProperties(courseEntity, courseResult);
        return Result.newSuccess(courseResult);
    }
}
