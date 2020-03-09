package com.github.jiangxch.courselearningmanagement.providerapi.enums;

import lombok.Getter;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:39
 */
public enum ResourceTypeEnum {
    HANDOUT(1, "讲义"),
    FINAL_EXAM_PAPER(2, "期末试卷"),
    ANSWERS_TO_EXERCISES(3, "课后答案")
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
