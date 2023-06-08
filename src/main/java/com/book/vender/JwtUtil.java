package com.book.vender;

import com.book.infra.config.security.UserDetail;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * FIXME 简单版jwt
 *
 * @author jinx
 */
public class JwtUtil {

    public static String encode(Object obj) {
        byte[] bytes = SerialUtil.serial(obj).getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static UserDetail decode(String rawString) {
        try {
            String str = new String(Base64.getDecoder().decode(rawString));
            return SerialUtil.deSerial(str, UserDetail.class);
        } catch (Exception e) {
            return null;
        }
    }
}
