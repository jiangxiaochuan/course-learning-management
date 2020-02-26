package com.github.jiangxch.courselearningmanagement.common.result;

import com.github.jiangxch.courselearningmanagement.common.enums.ResultEnum;
import lombok.Data;

/**
 * controller 统一响应对象
 */
@Data
public class Result<T> {
    private Integer code;
    private String errMsg;
    private T data;

    public static <T> Result<T> newResult(ResultEnum resultEnum) {
        Result<T> result = new Result<T>();
        result.setCode(resultEnum.getCode());
        result.setErrMsg(resultEnum.getErrMsg());
        return result;
    }

    public static <T> Result<T> newResult(ResultEnum resultEnum, String errorMsg) {
        Result<T> result = newResult(resultEnum);
        result.setErrMsg(errorMsg);
        return result;
    }

    public static <T> Result<T> newSuccess(T data) {
        Result<T> result = newResult(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> newSuccess() {
        Result<T> result = newResult(ResultEnum.SUCCESS);
        return result;
    }

    public static <T> Result<T> newError(String msg) {
        Result<T> result = newResult(ResultEnum.SYSTEM_ERROR);
        result.setErrMsg(msg);
        return result;
    }

    /**
     * isSuccess会影响其他库,故意改为hasSuccess
     * @return
     */
    public boolean hasSuccess() {
        return ResultEnum.SUCCESS.getCode().equals(this.code);
    }

}
