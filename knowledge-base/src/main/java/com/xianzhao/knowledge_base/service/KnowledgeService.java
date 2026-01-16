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

    //模拟数据库存储内存中的知识列表
    //static静态变量：类加载时创建，所有对象共享同一份数据
    //在Spring中Service默认是单例模式的
    private static final List<Knowledge> KNOWLEDGE_STORE=new ArrayList<>();

    public Knowledge createDemoKnowledge() {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(1L);
        knowledge.setTitle("Spring Boot 入门");
        knowledge.setContent("这是第一篇知识");
        knowledge.setCreatedAt(LocalDateTime.now());
        return knowledge;
    }

    public Knowledge create(KnowledgeCreateRequest request){
        Knowledge knowledge=new Knowledge();
        knowledge.setId(System.currentTimeMillis());
        knowledge.setTitle(request.getTitle());
        knowledge.setContent(request.getContent());
        knowledge.setCreatedAt(LocalDateTime.now());
        KNOWLEDGE_STORE.add(knowledge);
        return knowledge;
    }

    public List<Knowledge> listKnowledge(){
        return KNOWLEDGE_STORE;
    }

    public Knowledge getById(Long id){
        //这段代码等价于：SELECT * FROM knowledge WHERE id = ?
        return KNOWLEDGE_STORE.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
