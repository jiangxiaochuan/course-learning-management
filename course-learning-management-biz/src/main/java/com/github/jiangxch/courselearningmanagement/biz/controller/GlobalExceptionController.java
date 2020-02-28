package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.enums.ResultEnum;
import com.github.jiangxch.courselearningmanagement.common.exception.MyException;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionController {

    // Spring MVC 参数不正确
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        return Result.newResult(ResultEnum.PARAM_ERROR, ResultEnum.PARAM_ERROR.getErrMsg() + ":" + ex.getMessage());
    }

    // jsr303 参数校验异常
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder();
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append(",").append(constraintViolation.getMessage()));
        return Result.newResult(ResultEnum.PARAM_ERROR,
                ResultEnum.PARAM_ERROR.getErrMsg() + ":" + detailMessage.toString());
    }


    // 数据库插入数据唯一约束异常 //TODO 该handler无法生效,不知道为什么
    @ResponseBody
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public Result SQLIntegrityConstraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        return Result.newResult(ResultEnum.SQL_UNIQUE_ERROR, ex.getMessage());
    }

    //业务异常
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Result exceptionHandler(HttpServletRequest req, MyException e) {
        return Result.newResult(e.getResultEnum());
    }

    // 其他异常
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        return Result.newResult(ResultEnum.SYSTEM_ERROR, ResultEnum.SYSTEM_ERROR.getErrMsg() + ":" + e.getMessage());
    }


}