package com.github.jiangxch.courselearningmanagement.provider.dao;

import com.github.jiangxch.courselearningmanagement.common.data.WxUserInfo;
import com.github.jiangxch.courselearningmanagement.common.utils.DateUtil;
import com.github.jiangxch.courselearningmanagement.provider.dao.common.BaseDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.UserEntity;
import com.github.jiangxch.courselearningmanagement.providerapi.enums.UserRoleTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午2:53
 */
@Component
public class UserEntityDao extends BaseDao<UserEntity> {
    UserEntity getUserEntityByOpenId(String openId) {
        return null;
    }

    public UserEntity createWxUserEntity(String openId, WxUserInfo wxUserInfo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOpenId(openId);
        userEntity.setNickname(wxUserInfo.getNickname());
        userEntity.setProfile(wxUserInfo.getAvatarUrl());
        userEntity.setUsername("");
        userEntity.setPassword("");
        userEntity.setRoleType(UserRoleTypeEnum.USER.getType());
        userEntity.setCreateTime(DateUtil.getUnix());
        userEntity.setUpdateTime(DateUtil.getUnix());
        return userEntity;
    }
}
