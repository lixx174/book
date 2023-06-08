package com.book.vender.wx;

import com.book.vender.SerialUtil;
import org.springframework.http.HttpMethod;

import java.net.URI;

/**
 * @author jinx
 */
public interface WxRequest {

    URI constructUri();

    Class<? extends WxResponse> expect();

    default String body() {
        return SerialUtil.serial(this);
    }

    default HttpMethod method() {
        return HttpMethod.POST;
    }
}
