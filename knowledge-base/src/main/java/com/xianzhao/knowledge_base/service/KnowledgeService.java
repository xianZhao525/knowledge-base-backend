package com.xianzhao.knowledge_base.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xianzhao.knowledge_base.dto.KnowledgeCreateRequest;
import com.xianzhao.knowledge_base.entity.Knowledge;
import com.xianzhao.knowledge_base.repository.KnowledgeRepository;

//告诉Spring：这是一个业务组件，请你在启动时帮我创建并管理这个对象
//这套机制叫IoC / 依赖注入（Dependency Injection）
@Service
public class KnowledgeService {

    private final KnowledgeRepository knowledgeRepository;
    public KnowledgeService(KnowledgeRepository knowledgeRepository){
        this.knowledgeRepository=knowledgeRepository;
    }

    public Knowledge create(KnowledgeCreateRequest request){
        Knowledge knowledge=new Knowledge();
        knowledge.setTitle(request.getTitle());
        knowledge.setContent(request.getContent());
        knowledge.setAuthor(request.getAuthor());
        knowledge.setStatus("DRAFT");
        knowledge.setCreatedAt(LocalDateTime.now());
        return knowledgeRepository.save(knowledge);
    }

    //查询全部
    public List<Knowledge> listKnowledge(){
        return knowledgeRepository.findByStatusNot("DELETED");
    }

    //根据ID查询
    public Knowledge getById(Long id) {
        Knowledge knowledge = knowledgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("知识不存在"));

        if ("DELETED".equals(knowledge.getStatus())) {
            throw new RuntimeException("知识已被删除");
        }

        return knowledge;
    }


    public Knowledge publish(Long id){
        //knowledgeRepository.findById(id)，JPA 返回的是 Optional<Knowledge>,防止空指针
        //.orElseThrow(...),表示如果不存在，立即失败
        Knowledge knowledge=knowledgeRepository.findById(id).orElseThrow(() -> new RuntimeException("只是不存在"));
        if(!"DRAFT".equals(knowledge.getStatus())){
            throw new RuntimeException("只有草稿状态的知识才能发布");
        } 
        knowledge.setStatus("PUBLISHED");
        //因为JPA不会自动更新数据库，所以必须显式保存
        return knowledgeRepository.save(knowledge);
    }

    public void delete(Long id){
        Knowledge knowledge=knowledgeRepository.findById(id).orElseThrow(() -> new RuntimeException("知识不存在"));
        if("DELETED".equals(knowledge.getStatus())){
            throw new RuntimeException("知识已经被删除，不能重复删除");
        } else {
            knowledge.setStatus("DELETED");
            knowledgeRepository.save(knowledge);
        }
    }
}
