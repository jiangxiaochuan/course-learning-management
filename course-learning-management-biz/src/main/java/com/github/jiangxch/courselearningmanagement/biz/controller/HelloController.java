package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午7:59
 */
@RestController
@RequestMapping("/hello")
@Api(tags = "hello")
public class HelloController {
    @ApiOperation(value = "说hello")
    @GetMapping("")
    public Result<String> hello() {
        return Result.newSuccess("hello");
    }
}
