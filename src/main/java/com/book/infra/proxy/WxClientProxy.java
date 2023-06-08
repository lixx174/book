package com.book.infra.proxy;

import com.book.vender.SerialUtil;
import com.book.vender.wx.WxClient;
import com.book.vender.wx.WxRequest;
import com.book.vender.wx.WxResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * @author jinx
 */
@Slf4j
@RequiredArgsConstructor
public class WxClientProxy implements InvocationHandler {

    private final RestTemplate template;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (WxClient.class.equals(method.getDeclaringClass())) {
            if (args != null && args.length == 1 && args[0] instanceof WxRequest) {
                return execute((WxRequest) args[0]);
            }
            throw new UnsupportedOperationException("method not support");
        }

        // FIXME 应该调用proxy的方法 但是会栈溢出
        // 先使用this过渡一下
        return method.invoke(this, args);
    }


    private WxResponse execute(WxRequest request) {
        URI uri = request.constructUri();
        String body = request.body();

        log.info("===>  wx 请求地址 {}  请求参数{}", uri, body);

        String response;
        if (request.method() == HttpMethod.GET) {
            response = template.getForObject(uri, String.class);
        } else if (request.method() == HttpMethod.POST) {
            response = template.postForObject(uri, body, String.class);
        } else {
            throw new UnsupportedOperationException("httpMethod not support");
        }

        log.info("===>  wx 响应参数 {}", response);

        return SerialUtil.deSerial(response, request.expect());
    }
}
