package com.github.jiangxch.courselearningmanagement.common.data;

import com.github.jiangxch.courselearningmanagement.common.enums.UserRoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfo implements Serializable {

    // 用户id
    private String userId;

    /**
     * 用户角色类型
     * @see UserRoleTypeEnum
     */
    private Integer roleType;
}