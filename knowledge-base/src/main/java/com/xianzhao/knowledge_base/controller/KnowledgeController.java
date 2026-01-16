package com.xianzhao.knowledge_base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xianzhao.knowledge_base.common.ApiResponse;
import com.xianzhao.knowledge_base.dto.KnowledgeCreateRequest;
import com.xianzhao.knowledge_base.entity.Knowledge;
import com.xianzhao.knowledge_base.service.KnowledgeService;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// 表明这是一个控制器类，类中的方法是对外提供接口的，但绘制并不是页面而是json数据
@RestController

//表明给这个Controller统一加一个前缀路径，等价于这个类下面所有接口都以/knowledge开头
//在真实项目中会有很多模块，使用这种方法的话可以使结构清晰，一眼就知道接口是属于哪一块业务
@RequestMapping("/knowledge")

public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    //Controller 需要用 KnowledgeService，
    // 但不自己创建，
    // 而是让 Spring 注入进来
    // 这叫：构造器注入
    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }
    
    //当有人用Get请求访问/knowledge/test 时，执行这个方法
    //等价于@RequestMapping(value = "/test", method = RequestMethod.GET)
    // 这是因为Spring Boot帮我们简化了
    @GetMapping("/test")

    // 意味着：外层是统一格式,内层是业务数据
    // 这就是：“接口协议”和“业务模型”解耦
    public ApiResponse<Knowledge> test(){
        return ApiResponse.success(knowledgeService.createDemoKnowledge());
    }
    
    @GetMapping("/list")
    // Controller 返回的是：
    // ApiResponse<List<Knowledge>>
    // 意味着：外层统一格式,内层是列表,这是泛型的嵌套使用
    public ApiResponse<List<Knowledge>> list(){

        // 只做三件事：
        // 接请求
        // 调 Service
        // 包装返回
        // 这叫：薄 Controller，厚 Service,是后端项目的黄金准则。
        List<Knowledge> list=knowledgeService.listKnowledge();
        return ApiResponse.success(list);
    }

    //URL 中的 {id} 是一个占位符
    // 当访问：GET /knowledge/1
    // Spring 会理解为：id = 1
    @GetMapping("/{id}")
    //告知了参数 id 是从 URL 路径中的{id}获取的
    public ApiResponse<Knowledge> detail(@PathVariable Long id){
        Knowledge knowledge=knowledgeService.getById(id);

        //外层：接口协议（code / message）
        //内层：业务数据（Knowledge）
        return ApiResponse.success(knowledge);
    }

    //@PostMapping
    //HTTP POST,路径 = /knowledge
    @PostMapping
    
    // @RequestBody KnowledgeCreateRequest request
    // Spring 自动把 JSON 转成 Java 对象
    // public ApiResponse<Knowledge> create(@RequestBody KnowledgeCreateRequest request){
    //     Knowledge knowledge=knowledgeService.create(request);
    //     return ApiResponse.success(knowledge);
    // }

    public ApiResponse<Knowledge> create(@Valid @RequestBody KnowledgeCreateRequest request){
        Knowledge knowledge=knowledgeService.create(request);
        return ApiResponse.success(knowledge);
    }
}
