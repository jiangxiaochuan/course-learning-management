# 毕业设计: 课程学习管理系统

# 整体设计
项目:
- Java后台: 提供后台API
- 微信小程序端: 普通用户访问,可以查看课程资源
- 后台管理web: 用于上传课程资源

# 登录设计
**用户角色类别**:
- 普通用户: 具备访问小程序,后台管理web的权限
- 后台管理员: 比普通用户多出`审核普通用户上传的课程资源`的权限
- 超级管理员: 比后台管理员多出`后台管理员管理(后台管理员的CRUD)`权限

不需要考虑后期的角色扩展

**登录需求**:
- 微信小程序: 微信小程序方式登录
- 后台管理Web: 用户名密码登录

因微信扫码登录需要企业支持,所以还是选择用户名密码登录,两个登录方式需要2个接口支持

**Java后台设计**:
1. 登录方式: JWT进行登录
2. JWT中payload结构:
    ```java
    class JwtData{
     // 用于DB查询用户
     private String id;
     // 用户角色类型(1:超级管理员2:后台管理员3:普通用户)
     private Integer roleType;
    }
    ```
3. 使用`Spring Filter`进行认证和鉴权,可能出现的情况如下:
    1. **解析token失败**: 即无法获取用户id,用户未登录,controller使用抛出异常方式进行响应,返回 “您还未登录”。
    2. **进行权限验证**: controller中通过roleType进行鉴权,权限不通过返回"您没有该权限"。

# OOS设计
即Object Storage Service, 文件上传,本项目中用于存储课程资源文件,如PPT讲义,Word期末考试试卷,视频教程等文件资源

**文件上传需求**
- 满足基本文件上传即可

暂时使用`腾讯云COS`进行文件上传,后面也可以考虑`华为云的OBS`,考虑到需要支持多个OOS服务,所以在开发时要将关键的接口抽象出来。

# 项目部署
**模块说明**:
- course-learning-management-common: 公用模块
- course-learning-management-provider-api: service接口模块
- course-learning-management-provider: service实现模块
- course-learning-management-biz: controller层
- course-learning-management-remote: 使用到的第三方API
- course-learning-management-oos: 整合第三方的OOS服务
- course-learning-management-all: 打包的Spring Boot模块,需要添加Spring Boot maven插件
**项目打包**:

整个项目打成一个jar包,使用内嵌的Tomcat

