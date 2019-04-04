package com.sky.team.config.Jwt;


import com.sky.team.business.util.JwtUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    private String protectUrlPattern = "/api/**";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if(request.getServletPath().equals("/api/login")||request.getServletPath().equals("/api/getEmail")||request.getServletPath().equals("/api/isUserId")||request.getServletPath().equals("/api/register")){
                filterChain.doFilter(request, response);
            }else{
                if(isProtectedUrl(request)) {
                    String token = request.getHeader("Authorization");
                    //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                    JwtUtil.validateToken(token);
                    filterChain.doFilter(request, response);
                }
            }
        } catch (Exception e) {
            System.out.println("过滤器异常");
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api

    }
    private boolean isProtectedUrl(HttpServletRequest request) {

        return pathMatcher.match(protectUrlPattern, request.getServletPath());
    }
}
