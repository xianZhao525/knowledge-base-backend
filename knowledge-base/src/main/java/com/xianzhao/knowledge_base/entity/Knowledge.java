package com.xianzhao.knowledge_base.entity;

import java.lang.String;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;  
//告诉JPA这是一个实体类，对应数据库中的一张表
@Entity
//指定这张表的名字是knowledge
@Table(name="knowledge")
public class Knowledge {
    @Id
    //主键生成策略：数据库自增
    //IDENTITY = MySQL 的 AUTO_INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 255)
    private String title;//标题

    @Column(columnDefinition = "TEXT")
    private String content;//内容

    @Column(name = "created_at")
    private LocalDateTime createdAt;//创建时间

    private String author;
    private String status;

    public Knowledge(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public LocalDateTime getCreateAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }
}