package com.book.infra.config.security;


import com.book.application.Result;
import com.book.vender.JwtUtil;
import com.book.vender.ResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author jinx
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String[] PERMIT_URLS = {"/token", "/docs/**"};
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (Arrays.stream(PERMIT_URLS).noneMatch(i -> matcher.match(i, uri))) {
            String token = request.getHeader("Authorization");
            if (StringUtils.hasText(token)) {
                token = token.substring(7);
                UserDetail detail = JwtUtil.decode(token);

                if (detail != null) {
                    request.setAttribute("userId", detail.getId());

                    // 添加认证通过信息
                    AbstractAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(detail, "", null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    filterChain.doFilter(request, response);
                    return;
                }
            }
            ResponseUtil.response(response, Result.error(HttpStatus.UNAUTHORIZED));
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
