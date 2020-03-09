package com.github.jiangxch.courselearningmanagement.provider.entity;

import com.github.jiangxch.courselearningmanagement.provider.entity.common.BaseAliasEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity(value = "user", noClassnameStored = true)
public class UserEntity extends BaseAliasEntity {
    @Id
    private String id;
    private String openId;
    private String nickname;
    private String profile;
    private String username;
    private String password;
    /** 角色类型
     * {@link com.github.jiangxch.courselearningmanagement.providerapi.enums.UserRoleTypeEnum}
     */
    private Integer roleType;

    private String attach;
}