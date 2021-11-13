package com.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public String NullPointExceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("空指针异常，原因是：{}",e.getMessage());
        return "空指针异常";
    }

}
