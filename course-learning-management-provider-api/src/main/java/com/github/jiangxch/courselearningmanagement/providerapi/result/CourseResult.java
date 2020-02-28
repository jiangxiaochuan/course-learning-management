package com.github.jiangxch.courselearningmanagement.providerapi.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午11:14
 */
@Data
public class CourseResult implements Serializable {
    private String id;
    // 学校名称
    private String schoolName;
    // 学院名称
    private String academyName;
    // 专业名称
    private String majorName;
    // 课程名称
    private String courseName;
    /**
     * 是否是该专业必修课程
     * @see com.github.jiangxch.courselearningmanagement.common.enums.YesOrNoEnum
     */
    private Integer isObligatory;
}
