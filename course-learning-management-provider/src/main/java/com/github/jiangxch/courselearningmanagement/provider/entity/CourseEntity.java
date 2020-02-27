package com.github.jiangxch.courselearningmanagement.provider.entity;

import com.github.jiangxch.courselearningmanagement.provider.entity.common.BaseAliasEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity(value = "course", noClassnameStored = true)
public class CourseEntity extends BaseAliasEntity {
    @Id
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
     * @see com.github.jiangxch.courselearningmanagement.common.enums.common.YesOrNoEnum
     */
    private Integer isObligatory;
}