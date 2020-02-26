package com.github.jiangxch.courselearningmanagement.providerapi.arg;

import lombok.Data;

@Data
public class WxLoginArg {
    private String code;
    private String rawData;
    private String signature;
}