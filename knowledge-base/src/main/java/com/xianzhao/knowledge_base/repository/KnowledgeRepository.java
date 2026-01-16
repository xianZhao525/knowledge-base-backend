package com.xianzhao.knowledge_base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xianzhao.knowledge_base.entity.Knowledge;

public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {

     List<Knowledge> findByStatusNot(String status);
}
