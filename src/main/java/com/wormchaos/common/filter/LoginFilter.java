package com.wormchaos.common.filter;

import com.wormchaos.common.NnUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by wormchaos on 2015/6/13.
 */
public class LoginFilter implements Filter {

    private static final String LOGIN_URI = "/nuannuan/user/login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        // 如果不是登录页面则判断是否登录
        if (!isLogin(httpRequest)) {
            // 重定向到登录页面
            // httpResponse.addHeader(UnoConstants.REFERER, httpRequest.getHeader(UnoConstants.REFERER));
            httpResponse.sendRedirect(LOGIN_URI + "?referer="
                    + URLEncoder.encode(httpRequest.getRequestURL().toString(), "utf-8"));
            return;
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    /**
     * 判断是否登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) {
        return null != request.getSession().getAttribute(NnUtils.SESSION_USER);
    }

    @Override
    public void destroy() {

    }
}
