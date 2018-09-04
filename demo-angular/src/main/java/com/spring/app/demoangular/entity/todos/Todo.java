package com.spring.app.demoangular.entity.todos;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sample_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "completed")
    private boolean completed = false;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    public Todo(){
        super();
    }

    public Todo(String title){
        this.title = title;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
}
