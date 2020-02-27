package com.github.jiangxch.courselearningmanagement.providerapi.arg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "小程序登陆表单")
public class WxLoginArg {
    @ApiModelProperty(value = "小程序接口获得的 code", required = true, example = "ELASJDL423JJKH")
    @NotEmpty(message = "code 不能为空")
    private String code;

    @ApiModelProperty(value = "小程序接口获得的 rowData", required = true, example = "{nickName: '小白'}")
    @NotEmpty(message = "rawData 不能为空")
    private String rawData;

    @ApiModelProperty(value = "小程序接口获得的 signature,用来对 rawData 进行验证", required = false, example = "")
    private String signature;
}