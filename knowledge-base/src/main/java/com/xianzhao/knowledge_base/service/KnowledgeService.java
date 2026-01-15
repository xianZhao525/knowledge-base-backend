package com.xianzhao.knowledge_base.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.xianzhao.knowledge_base.entity.Knowledge;

//告诉Spring：这是一个业务组件，请你在启动时帮我创建并管理这个对象
//这套机制叫IoC / 依赖注入（Dependency Injection）
@Service
public class KnowledgeService {

    public Knowledge createDemoKnowledge() {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(1L);
        knowledge.setTitle("Spring Boot 入门");
        knowledge.setContent("这是我的第一个项目");
        knowledge.setCreatedAt(LocalDateTime.now());
        return knowledge;
    }
}
