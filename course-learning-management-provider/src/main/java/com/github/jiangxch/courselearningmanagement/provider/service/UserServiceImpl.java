package com.github.jiangxch.courselearningmanagement.provider.service;

import com.github.jiangxch.courselearningmanagement.common.data.AuthCode2Session;
import com.github.jiangxch.courselearningmanagement.common.data.AuthInfo;
import com.github.jiangxch.courselearningmanagement.common.data.WxUserInfo;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.common.utils.JsonUtil;
import com.github.jiangxch.courselearningmanagement.common.utils.JwtUtil;
import com.github.jiangxch.courselearningmanagement.common.utils.OkHttpUtil;
import com.github.jiangxch.courselearningmanagement.provider.dao.UserEntityDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.UserEntity;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.AdminRegisterOrUpdateArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WebLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WxLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.UserInfoResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author: sanjin
 * @date: 2020/2/27 下午12:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Autowired
    private UserEntityDao userEntityDao;

    @Override
    public Result<String> wxLogin(WxLoginArg wxLoginArg) {
        // 小程序登陆
        // auth.code2Session 地址
        String url = String.format(
                " https://api.weixin.qq.com/sns/jscode2session?" +
                        "appid=%s&" + // appid
                        "secret=%s&" + // secret
                        "js_code=%s&" + // code
                        "grant_type=authorization_code",
                appId, secret, wxLoginArg.getCode()
        );

        String jsonData = OkHttpUtil.doGet(url);
        AuthCode2Session authCode2Session = JsonUtil.jsonToPojo(jsonData, AuthCode2Session.class);
        // 验证 auth.code2Session 请求是否发送成功，0 表示成功，详见小程序官方文档
        if (authCode2Session == null || authCode2Session.getErrcode() != 0) {
            return Result.newError("服务端请求 auth.code2Session 接口错误");
        }
        // 根据  openid 判断该用户是否存在
        UserEntity userEntity = userEntityDao.findOne("openId", authCode2Session.getOpenid());

        if (userEntity == null) {
            // 用户不存在，进行保存
            // 从表单获取用户数据 nickname,gender,language,city,province,country,avatarUrl,
            WxUserInfo wxUserInfo = JsonUtil.jsonToPojo(wxLoginArg.getRawData(), WxUserInfo.class);
            if (wxUserInfo == null) {
                return Result.newError("rawData 错误,用户信息不能为空");
            }
            userEntity = userEntityDao.createWxUserEntity(authCode2Session.getOpenid(), wxUserInfo);
        }
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(userEntity, result);

        return generateToken(result);
    }

    @Override
    public Result<String> generateToken(UserInfoResult data) {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUserId(data.getId());
        authInfo.setRoleType(data.getRoleType());
        String token = JwtUtil.generateToken(authInfo);
        return Result.newSuccess(token);
    }

    @Override
    public Result<String> adminLogin(WebLoginArg arg) {
        UserEntity user = userEntityDao.getByUsernamePassword(arg.getUsername(), arg.getPassword());
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(user, result);
        return generateToken(result);
    }

    @Override
    public Result<Void> adminRegisterOrUpdateArg(AdminRegisterOrUpdateArg arg, String userId) {
        UserEntity user = userEntityDao.findOne("id", userId);
        user.setUsername(arg.getUsername());
        user.setPassword(arg.getPassword());
        userEntityDao.save(user);
        return Result.newSuccess();
    }

    @Override
    public Result<UserInfoResult> getUserResultById(String userId) {
        UserEntity user = userEntityDao.findOne("id", userId);
        if (user == null) {
            return Result.newSuccess();
        }
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(user, result);
        return Result.newSuccess(result);
    }
}
