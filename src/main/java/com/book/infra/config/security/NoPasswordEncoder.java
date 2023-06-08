package com.book.infra.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 默认使用该加密器 明文存储
 *
 * @author jinx
 */
@Component
public class NoPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword == null ? "" : rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        rawPassword = rawPassword == null ? "" : rawPassword;
        encodedPassword = encodedPassword == null ? "" : encodedPassword;

        return rawPassword.equals(encodedPassword);
    }
}
