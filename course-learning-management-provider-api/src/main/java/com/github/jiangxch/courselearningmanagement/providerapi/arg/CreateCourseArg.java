package com.github.jiangxch.courselearningmanagement.providerapi.arg;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午11:16
 */
@Data
public class CreateCourseArg implements Serializable {
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
