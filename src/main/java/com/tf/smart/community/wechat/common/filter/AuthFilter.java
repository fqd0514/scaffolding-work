package com.tf.smart.community.wechat.common.filter;

import com.tf.smart.community.wechat.common.utils.CacheUtil;
import com.tf.smart.community.wechat.common.utils.SessionUtils;
import com.tf.smart.community.wechat.entity.auth.ThreadSessionInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

import static com.tf.smart.community.wechat.common.constant.CommonConstant.WECHATTOKENKEY;

/***
 * 网关过滤器
 * @Author Leeyoung
 * @Date 2021/1/25
 **/
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "authFilter")
public class AuthFilter implements Filter {
    private static final TreeSet<String> NO_FILTER_URL = new TreeSet<>();

    @Autowired
    private CacheUtil cacheUtil;

    /**
     * 微信token过期时间，单位秒
     */
    @Value("${atToken.expireSeconds}")
    private long atTokenExpireSeconds;

    static {
        NO_FILTER_URL.add("/swagger-ui.html");
        NO_FILTER_URL.add("/swagger-resources");
        NO_FILTER_URL.add("/webjars");
        NO_FILTER_URL.add("/v2");
        NO_FILTER_URL.add("/api/wechat/login");
        NO_FILTER_URL.add("/doc.html");
        NO_FILTER_URL.add("/api/*");
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, Content-Type, credential, X-XSRF-TOKEN");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        // OPTION 请求
        if (StringUtils.equals(RequestMethod.OPTIONS.name(), request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String URI = request.getServletPath();
        String token = request.getHeader("Authorization");

        // 根据 NO_FILTER_URL 集合判断放行已配置的请求
        boolean flag = false;
        for (String url : NO_FILTER_URL) {
            if (URI.contains(url) || (url.contains("*") && URI.startsWith(url.replace("/*", "")))) {
                flag = true;
                break;
            }
        }
        if (flag) {
            chain.doFilter(request, response);
            return;
        }

        SessionUtils.threadLocal.set(new ThreadSessionInfo(token));
        if (StringUtils.isBlank(token)) {
            sendErrorMessage(response, "请先登录");
        } else {
            if (checkToken(token)) {
                chain.doFilter(request, response);
            } else {
                cacheUtil.del(WECHATTOKENKEY + token);
                sendErrorMessage(response, "Token 已过期，请重新登录！");
            }
        }
    }

    /**
     * 检查token是否有效
     * @param token
     * @return boolean
     * @Author Leeyoung
     * @Date 2020/10/9
     **/
    private boolean checkToken(String token) {
        if(cacheUtil.hasKey(WECHATTOKENKEY+token)){
            long l = cacheUtil.getExpire(token);
            if (l < atTokenExpireSeconds) {
                // 更新过期时间
                cacheUtil.expire(WECHATTOKENKEY+token, atTokenExpireSeconds);
            }
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void destroy() {
    }

    private void sendErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        response.setHeader("Cache-Control", "max-age=0, private, must-revalidate");
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);

        ServletOutputStream output = response.getOutputStream();
        output.write(message.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
