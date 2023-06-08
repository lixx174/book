package com.book.infra.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security运行原理：通过 SecurityFilterChain 中注册的一系列拦截器进行请求拦截
 * SecurityFilterChain 通过 httpSecurity 配置而来
 * SecurityFilterChain 可以配置多个，其内部有个 List<Filter>  又包含很多拦截器
 *
 * @author jinx
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;


    /**
     * 配置原理：
     * 1。 参考 SpringBootWebSecurityConfiguration.defaultSecurityFilterChain(HttpSecurity)
     * 每新增一个配置（eg: http.httpBasic(withDefaults())） 就会在http中新增一个 Configurer
     * 最后在http.build()时 会调用所有的 Configurer.config() 进行组件注册
     * <p>
     * remark：
     * 1。 使用自定义SecurityFilterChain配置时，发现少了几个拦截器。原因正是该原理。
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        WxAuthenticationFilter authenticationFilter = new WxAuthenticationFilter();
        JwtTokenFilter tokenFilter = new JwtTokenFilter();

        DefaultSecurityFilterChain defaultSecurityFilterChain = http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenFilter, WxAuthenticationFilter.class)
                .build();

        // http.build() 之前还未初始化AuthenticationManager  是在build的过程中初始化
        // 参考 org.springframework.security.config.annotation.web.builders.HttpSecurity.beforeConfigure
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        return defaultSecurityFilterChain;
    }

}
