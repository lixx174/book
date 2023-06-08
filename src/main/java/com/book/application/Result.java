package com.book.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Jinx
 **/
@Getter
@NoArgsConstructor
public class Result<T> {

    /**
     * 响应code
     */
    private int code = 200;

    /**
     * 提示信息
     */
    private String msg = "success";

    /**
     * 响应数据
     */
    private T data;


    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> ok() {
        return new Result<>();
    }

    public static <T> Result<T> error(HttpStatus httpStatus) {
        return new Result<>(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static <T> Result<T> error(HttpStatus httpStatus, String msg) {
        return new Result<>(httpStatus.value(), msg);
    }
}
