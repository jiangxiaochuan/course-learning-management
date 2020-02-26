package com.github.jiangxch.courselearningmanagement.provider.entity.enums;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午1:39
 */
public enum UserRoleTypeEnum {
    SUPER_ADMIN(1, "超级管理员"),
    ADMIN(2, "后台管理员"),
    USER(3, "普通用户")
    ;

    public Integer type;
    public String msg;

    UserRoleTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

}
