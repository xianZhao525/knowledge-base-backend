package com.xianzhao.knowledge_base.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xianzhao.knowledge_base.dto.KnowledgeCreateRequest;
import com.xianzhao.knowledge_base.entity.Knowledge;

//告诉Spring：这是一个业务组件，请你在启动时帮我创建并管理这个对象
//这套机制叫IoC / 依赖注入（Dependency Injection）
@Service
public class KnowledgeService {

    public Knowledge createDemoKnowledge() {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(1L);
        knowledge.setTitle("Spring Boot 入门");
        knowledge.setContent("这是第一篇知识");
        knowledge.setCreatedAt(LocalDateTime.now());
        return knowledge;
    }

    //为什么 Service 返回 List<Knowledge>？
    // Service 层的职责是：
    // “提供业务数据”
    // 不关心 HTTP,不关心 JSON,不关心 code / message
    public List<Knowledge> listKnowledge(){
        //为什么用 ArrayList？
        //List 是接口
        //ArrayList 是最常用实现
        //真实中,数据库查询结果最终也会映射成 List
        List<Knowledge> list=new ArrayList<>();
        Knowledge k1=new Knowledge();
        k1.setId(1L);
        k1.setTitle("Spring Boot 入门");
        k1.setContent("这是第一篇知识");
        k1.setCreatedAt(LocalDateTime.now());

        Knowledge k2=new Knowledge();
        k2.setId(2L);
        k2.setTitle("Java 基础语法");
        k2.setContent("这是第二篇知识");
        k2.setCreatedAt(LocalDateTime.now());

        list.add(k1);
        list.add(k2);

        return list;
    }

    //根据id查询单条Knowledge
    public Knowledge getById(Long id){

        //先用已有的list来模拟数据库
        List<Knowledge> list=listKnowledge();
        for(Knowledge knowledge:list){
            //为什么用equals而不是==？
            // Long是对象类型，==比较的是内存地址，equals比较的是值
            if(knowledge.getId().equals(id)){
                return knowledge;
            }
        }

        //找不到就返回null
        return null;
        //Service 层可以返回 null
        //Controller 层 不能直接把 null 返回给前端
    }

    public Knowledge create(KnowledgeCreateRequest request){
        Knowledge knowledge=new Knowledge();
        knowledge.setId(System.currentTimeMillis());
        knowledge.setTitle(request.getTitle());
        knowledge.setContent(request.getContent());
        knowledge.setCreatedAt(LocalDateTime.now());
        return knowledge;
    }
}
