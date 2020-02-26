package com.github.jiangxch.courselearningmanagement.providerapi.arg;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午9:54
 */
@Data
public class WebLoginArg implements Serializable {
    private String username;
    private String password;
}
