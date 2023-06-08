package com.book.vender;

import com.book.application.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

/**
 * @author jinx
 */
public class ResponseUtil {

    @SneakyThrows
    public static void response(HttpServletResponse response, Result<?> result) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(result.getCode());

        response.getWriter().write(SerialUtil.serial(result));

        response.getWriter().flush();
        response.getWriter().close();
    }

}
