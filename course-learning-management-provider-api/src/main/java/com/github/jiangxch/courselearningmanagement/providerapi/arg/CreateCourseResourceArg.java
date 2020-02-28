package com.github.jiangxch.courselearningmanagement.providerapi.arg;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/27 上午11:21
 */
@Data
public class CreateCourseResourceArg implements Serializable {
    private String resourceName;
    /**
     * {@link com.github.jiangxch.courselearningmanagement.providerapi.enums.ResourceTypeEnum}
     */
    private Integer resourceType;
    // 资源所属学校名称
    private String schoolName;
    // 上传的文件
    private MultipartFile file;
    // 资源属于哪个课程
    private String courseId;
    private String courseName;
}
