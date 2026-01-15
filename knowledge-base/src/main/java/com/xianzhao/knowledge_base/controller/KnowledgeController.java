package com.xianzhao.knowledge_base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xianzhao.knowledge_base.entity.Knowledge;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 表明这是一个控制器类，类中的方法是对外提供接口的，但绘制并不是页面而是json数据
@RestController
//表明给这个Controller统一加一个前缀路径，等价于这个类下面所有接口都以/knowledge开头
//在真实项目中会有很多模块，使用这种方法的话可以使结构清晰，一眼就知道接口是属于哪一块业务
@RequestMapping("/knowledge")

public class KnowledgeController {

    //当有人用Get请求访问/knowledge/test 时，执行这个方法
    //等价于@RequestMapping(value = "/test", method = RequestMethod.GET)
    // 这是因为Spring Boot帮我们简化了
    @GetMapping("/test")
    public Knowledge test(){
        Knowledge k=new Knowledge();
        k.setId(1L);
        k.setTitle("Spring Boot 入门");
        k.setContent("这是我的第一个项目");
        k.setCreatedAt(LocalDateTime.now());
        return k;
    }
    
}
