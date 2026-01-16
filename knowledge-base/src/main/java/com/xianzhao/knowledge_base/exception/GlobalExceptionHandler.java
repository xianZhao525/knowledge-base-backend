package com.xianzhao.knowledge_base.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xianzhao.knowledge_base.common.ApiResponse;

import jakarta.validation.ConstraintViolationException;

//@RestControllerAdvice=@ControllerAdvice + @ResponseBody
//这是一个全局Controller级别的拦截器，所有Controller抛出的异常都会被这里捕获并处理
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理@RequestBody参数校验异常
    //@ExceptionHandler(MethodArgumentNotValidException.class)意思是当某种异常出现时就有这个方法来处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException ex){
        String message=ex.getBindingResult()
                    .getFieldError()//拿到哪个错误字段
                    .getDefaultMessage();//拿到我所写的message="xxx"的内容

        return ApiResponse.error(40001, message);
    }

    //处理路径参数、查询参数校验失败
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Void> handleConstraintViolation(ConstraintViolationException ex){
        return ApiResponse.error(40001, ex.getMessage());
    }

    //处理所有未知异常
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception ex){
        ex.printStackTrace();
        return ApiResponse.error(50000, "服务器内部错误");
    }
}
