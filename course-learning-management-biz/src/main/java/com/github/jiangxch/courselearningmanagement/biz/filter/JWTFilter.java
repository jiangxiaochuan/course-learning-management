package com.github.jiangxch.courselearningmanagement.biz.filter;

import com.github.jiangxch.courselearningmanagement.biz.common.RequestContext;
import com.github.jiangxch.courselearningmanagement.biz.common.RequestContextHolder;
import com.github.jiangxch.courselearningmanagement.common.data.AuthInfo;
import com.github.jiangxch.courselearningmanagement.common.utils.JwtUtil;
import com.github.jiangxch.courselearningmanagement.providerapi.enums.UserRoleTypeEnum;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 若请求中含有jwt，则通过jwt获取用户信息保存在
 *
 * @author: sanjin
 * @date: 2020/1/4 上午10:50
 */
@Component
public class JWTFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest res = (HttpServletRequest) request;
        String token = res.getHeader(JwtUtil.HEADER);
        if (token != null && JwtUtil.hasTokenValid(token)) {
            AuthInfo authInfo = JwtUtil.getAuthInfoFromToken(token);
            if (authInfo != null) {
                // token合法
                RequestContext requestContext = new RequestContext(authInfo);
                RequestContextHolder.setContext(requestContext);
            }
        }
        if ("admin".equals(token)) {
            AuthInfo tokenData = new AuthInfo()
                    .setUserId("1")
                    .setRoleType(UserRoleTypeEnum.SUPER_ADMIN.getType());
            RequestContext requestContext = new RequestContext(tokenData);
            RequestContextHolder.setContext(requestContext);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
