package com.github.jiangxch.courselearningmanagement.providerapi.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/27 上午11:41
 */
@Data
public class CourseResourceResult implements Serializable {
    private String id;
    private String resourceName;
    /**
     * {@link com.github.jiangxch.courselearningmanagement.providerapi.enums.ResourceTypeEnum}
     */
    private Integer resourceType;
    private String resourceTypeMsg;
    // 资源所属学校名称
    private String schoolName;
    // 资源上传者userId
    private UserInfoResult userInfoResult;
    // 资源URL
    private String url;
    // 资源文件后缀名(pdf,word,xls等)
    private String ext;
    // 资源属于哪个课程
    private CourseResult courseResult;
}
