package com.github.jiangxch.courselearningmanagement.providerapi.enums;

import lombok.Getter;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:39
 */
public enum ResourceTypeEnum {
    SUPER_ADMIN(1, "PPT讲义"),
    ADMIN(2, "Word期末试卷"),
    USER(3, "Word课后答案")
    ;

    @Getter
    private Integer type;
    @Getter
    private String msg;

    ResourceTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static ResourceTypeEnum getByType(Integer type) {
        for (ResourceTypeEnum resourceTypeEnum : values()) {
            if (resourceTypeEnum.type.equals(type)) {
                return resourceTypeEnum;
            }
        }
        return null;
    }
}
