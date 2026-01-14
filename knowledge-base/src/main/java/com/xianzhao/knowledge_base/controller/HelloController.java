package com.xianzhao.knowledge_base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xianzhao.knowledge_base.common.ApiResponse;

@RestController
public class HelloController {

    @GetMapping("/")
    public ApiResponse<String> index(){
        return ApiResponse.success("Knowledge Base Service is running!");
    }

}
