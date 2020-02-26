package com.github.jiangxch.courselearningmanagement.common.enums;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:39
 */
public enum ResourceTypeEnum {
    SUPER_ADMIN(1, "PPT讲义"),
    ADMIN(2, "Word期末试卷"),
    USER(3, "Word课后答案")
    ;

    public Integer type;
    public String msg;

    ResourceTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
