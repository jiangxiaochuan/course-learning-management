package com.github.jiangxch.courselearningmanagement.providerapi.result;

import com.sun.corba.se.spi.ior.ObjectId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午12:45
 */
@Data
public class UserInfoResult implements Serializable {
    private ObjectId id;
    private String nickname;
    private String profile;
    private String username;
    private Integer roleType;
    private Long ct;
    private Long ut;
}
