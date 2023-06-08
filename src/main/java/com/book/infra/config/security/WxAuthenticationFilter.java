package com.book.infra.config.security;

import com.book.application.Result;
import com.book.vender.JwtUtil;
import com.book.vender.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

/**
 * @author jinx
 */
public class WxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected WxAuthenticationFilter() {
        // 只拦截 /token 的POST请求
        super(new AntPathRequestMatcher("/token", HttpMethod.POST.name()));

        // 注册认证失败处理器
        setAuthenticationFailureHandler((request, response, exception) ->
                ResponseUtil.response(response, Result.error(HttpStatus.FORBIDDEN, exception.getMessage())));
        // 注册认证成功处理器
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            String token = JwtUtil.encode(authentication.getPrincipal());
            ResponseUtil.response(response, Result.ok(token));
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String wxLoginCode = request.getParameter("code");
        String inviteCode = request.getParameter("inviteCode");
        if (StringUtils.hasText(wxLoginCode)) {
            AuthenticationManager authenticationManager = getAuthenticationManager();
            if (authenticationManager == null) {
                throw new AuthenticationServiceException("authenticationManager must be config");
            }

            String principle = wxLoginCode + "," + inviteCode;
            UsernamePasswordAuthenticationToken authRequest
                    = UsernamePasswordAuthenticationToken.unauthenticated(principle, "");

            return authenticationManager.authenticate(authRequest);
        }

        throw new BadCredentialsException("wx login js_code can't be null");
    }


    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
