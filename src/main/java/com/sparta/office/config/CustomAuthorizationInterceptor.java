package com.sparta.office.config;

import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomAuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = jwtUtil.getJwtFromHeader(request);
        if (jwt == null || !jwtUtil.validateToken(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing JWT Token");
            return false;
        }

        Claims claims = jwtUtil.getUserInfoFromToken(jwt);
        AdminRoleEnum role = AdminRoleEnum.valueOf(claims.get(JwtUtil.AUTHORIZATION_KEY, String.class));
        String httpMethod = request.getMethod();

        // POST, GET 요청은 모든 관리자 접근 허용
        if ("POST".equals(httpMethod) || "GET".equals(httpMethod)) {
            return true;
        }

        // PUT, DELETE 요청은 최고등급 관리자(MANAGER)만 허용
        if (("PUT".equals(httpMethod) || "DELETE".equals(httpMethod)) && role == AdminRoleEnum.MANAGER) {
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 실행 후 처리 (필요한 경우)
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 요청 처리 완료 후 처리 (필요한 경우)
    }
}
