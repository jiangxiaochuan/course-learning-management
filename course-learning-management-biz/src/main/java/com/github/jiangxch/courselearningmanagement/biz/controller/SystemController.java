package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午9:31
 */
@RestController
@RequestMapping("/system")
@Api(tags = "系统相关")
public class SystemController extends BaseController{
    @Autowired(required = false)
    private SystemService systemService;

    @ApiOperation(value = "获取全国高校名称")
    @PostMapping("/listChinaUniversityNames")
    public Result<List<String>> listChinaUniversityNames() {
        return systemService.listChinaUniversityNames();
    }

}
