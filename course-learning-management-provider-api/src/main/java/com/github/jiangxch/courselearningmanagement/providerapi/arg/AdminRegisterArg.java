package com.github.jiangxch.courselearningmanagement.providerapi.arg;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午1:28
 */
@Data
public class AdminRegisterArg implements Serializable {
    private String username;
    private String password;
}
