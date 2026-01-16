package com.xianzhao.knowledge_base.dto;

import jakarta.validation.constraints.NotBlank;
//这是一个接口输入模型，有以下优点：
//接口参数可控
//不暴露内部字段
//后期改数据库不影响接口
public class KnowledgeCreateRequest {

    @NotBlank(message="title不能为空")
    private String title;
    @NotBlank(message="content不能为空")
    private String content;
    @NotBlank(message="author不能为空")
    private String author;

    //Spring通过它反射取值
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

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author=author;
    }
}
