package com.github.jiangxch.courselearningmanagement.providerapi.enums;

import lombok.Getter;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:39
 */
public enum UserRoleTypeEnum {
    SUPER_ADMIN(1, "超级管理员"),
    ADMIN(2, "后台管理员"),
    USER(3, "普通用户")
    ;

    @Getter
    private Integer type;
    @Getter
    private String msg;

    UserRoleTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
