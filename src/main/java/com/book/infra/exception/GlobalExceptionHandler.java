package com.book.infra.exception;

import com.book.application.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jinx
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Result<Void> unprocessableEntity(Exception e) {
        e.printStackTrace();
        return Result.error(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }

    @ExceptionHandler({UnsupportedOperationException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> badRequestException(Exception e) {
        e.printStackTrace();
        return Result.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> exception(Exception e) {
        e.printStackTrace();

        if (e instanceof BindingResult) {
            String detail = ((BindingResult) e).getFieldErrors().get(0).getDefaultMessage();
            return Result.error(HttpStatus.UNPROCESSABLE_ENTITY, detail);
        }

        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
