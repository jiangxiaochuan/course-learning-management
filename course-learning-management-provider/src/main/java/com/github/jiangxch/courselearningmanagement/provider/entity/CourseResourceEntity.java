package com.github.jiangxch.courselearningmanagement.provider.entity;

import com.github.jiangxch.courselearningmanagement.provider.entity.common.BaseAliasEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity(value = "course_resource", noClassnameStored = true)
public class CourseResourceEntity extends BaseAliasEntity {
    @Id
    private ObjectId id;
    private String resourceName;
    /**
     * {@link com.github.jiangxch.courselearningmanagement.provider.entity.enums.ResourceTypeEnum}
     */
    private Integer resourceType;
    // 资源所属学校名称
    private String schoolName;
    // 资源上传者userId
    private ObjectId userId;
    // 资源URL
    private String url;
    // 资源文件后缀名(pdf,word,xls等)
    private String ext;
    // 资源属于哪个课程
    private ObjectId courseId;
}