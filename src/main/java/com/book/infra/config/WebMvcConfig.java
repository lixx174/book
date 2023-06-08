package com.book.infra.config;

import com.book.BookProperties;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jinx
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final BookProperties properties;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE");
    }

    @Override
    public void configurePathMatch(@NotNull PathMatchConfigurer configurer) {
        if (StringUtils.hasText(properties.getApiPrefix())) {
            configurer.addPathPrefix(
                    properties.getApiPrefix(),
                    c -> c.isAnnotationPresent(RestController.class)
            );
        }
    }
}
