package com.book.vender;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author jinx
 */
public class Base64Util {

    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String rawString) {
        return new String(Base64.getDecoder().decode(rawString));
    }

    public static Long decodeToLong(String rawString) {
        return Long.parseLong(new String(Base64.getDecoder().decode(rawString)));
    }

    public static void main(String[] args) {
        System.out.println(Base64Util.encode("1"));
    }
}
