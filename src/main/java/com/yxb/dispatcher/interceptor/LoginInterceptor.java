package com.yxb.dispatcher.interceptor;

import com.yxb.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yxb on  2018/2/26
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("preHandler >>>");
        String uri = request.getRequestURI();
        String type = request.getHeader("X-Requested-With");
        log.debug(uri);
        // 判断是否需要过滤
        //System.out.println( "uri = " + uri );
        // 白名单
        ServletContext application = request.getSession().getServletContext();
        String appPath = (String) application.getAttribute("APP_PATH");
        Set<String> whiteListSet = new HashSet<>();
        whiteListSet.add(appPath+"/index.jsp");
        whiteListSet.add(appPath+"/userController/login.do");
        whiteListSet.add(appPath+"/toLogin.do");

        if (whiteListSet.contains(uri)) {
            log.debug("white List...");
            return true;
        } else {
            /*
             * 判断当前用户是否登陆，判断session中是否包含用户信息
			 * 如果登陆，那么请求继续访问，
			 * 如果没有登陆，那么需要跳转回到登陆页面
			 */
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("userInfo");

            if (loginUser == null) {
                if(StringUtils.isNotBlank(type)) {
                    //ajax请求需要在页面重定向
                    response.getWriter().write("toLogin");
                    return false;
                } else {
                    //如果没有登陆，那么需要跳转回到登陆页面
                    log.debug("not login redirect to login page...");
                    response.sendRedirect(appPath+"/index.jsp");
                    return false;
                }
            }
            log.debug("access preHandler <<<");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
