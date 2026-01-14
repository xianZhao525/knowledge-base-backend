package com.xianzhao.knowledge_base.common;

public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    public ApiResponse(){
    }

    public ApiResponse(int code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    // 使用T，表示data类型暂定，这是一个泛型方法，接受一个T类型的数据
    // 并返回一个包着T的ApiResponse
    // 这是一个类型占位符
    // 用的时候才决定T是什么
    // 让接口返回更加安全，清晰，专业
    public static <T> ApiResponse<T> error(int code,String message){
        return new ApiResponse<>(code,message,null);
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code=code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data=data;
    }
}
