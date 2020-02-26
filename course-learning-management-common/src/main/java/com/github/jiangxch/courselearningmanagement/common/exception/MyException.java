package com.github.jiangxch.courselearningmanagement.common.exception;

import com.github.jiangxch.courselearningmanagement.common.enums.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyException extends RuntimeException {
    private ResultEnum resultEnum;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getErrMsg());
        this.resultEnum = resultEnum;
    }
}