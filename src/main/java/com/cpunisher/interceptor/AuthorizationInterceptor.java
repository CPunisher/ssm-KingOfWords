package com.cpunisher.interceptor;

import com.cpunisher.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "必须先登录");
            request.getRequestDispatcher("loginForm").forward(request, response);
            return false;
        }
        return true;
    }
}
