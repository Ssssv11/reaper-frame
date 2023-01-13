package com.ssssv.common;

import com.ssssv.bean.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdaptController {

    @ExceptionHandler({RuntimeException.class})
    public Result runtimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler({Exception.class})
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error();
    }
}
