package com.xianzhao.knowledge_base.entity;

import java.lang.String;
import java.time.LocalDateTime;  

public class Knowledge {
    private Long id;
    private String title;//标题
    private String content;//内容
    private LocalDateTime createdAt;//创建时间

    public Knowledge(){

    }

    public Long getId(){
        return id;
    }

    public void setIdf(Long id){
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
}