package com.github.jiangxch.courselearningmanagement.provider.entity.enums.common;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:39
 */
public enum YesOrNoEnum {
    YES(1, "是"),
    NO(2, "不是"),
    ;

    public Integer type;
    public String msg;

    YesOrNoEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
