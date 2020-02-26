package com.github.jiangxch.courselearningmanagement.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 统一错误码响应
 */
@ToString
public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统错误,请联系管理员"),
    SUCCESS(1, "成功"),
    PARAM_ERROR(2, "输入参数错误,请校验参数重新输入"),
    SQL_UNIQUE_ERROR(3, "数据库已有重复值"),
    REMOTE_ERROR(4, "远程调用失败"),
    AUTHENTICATION_ERROR(5, "您还未登录"),
    USER_NOT_EXIST(6, "用户不存在"),
    NO_CAMPUS_CERTIFICATION(7, "未进行校园认证"),
    FILE_UPLOAD_FAILED(8, "当前文件上传失败,请重新上传"),
    NO_SLIDE_MSG(9,"轮播图不存在"),
    NO_GOODS_MSG(10,"商品不存在"),
    REQUEST_LOGIN_URL_WITH_COOKIE(11, "携带cookie访问登录表单页面,因直接跳转到登录成功页面,无法获取_it参数")
    ;

    @Getter
    private Integer code;
    @Getter
    private String errMsg;

    ResultEnum(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }
}
