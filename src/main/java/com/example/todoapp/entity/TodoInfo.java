package com.example.todoapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

@Entity
@Table(name = "todoinfo")
@Getter
@Setter
public class TodoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    @Column(nullable = false, length=100)
    private String todoText;
    @Column(nullable = false)
    private boolean todoDone;
    @Column(nullable = false)
    private LocalDate todoLimitDate;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime updateAt;

    /**
     * 新規作成時
     */
    @PrePersist
    protected void onCreate(){
        this.todoDone = false;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    /**
     * 更新時
     */
    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public TodoInfo() {}

    public TodoInfo(String todoText, LocalDate todoLimitDate) {
        this.todoText = todoText;
        this.todoLimitDate = todoLimitDate;
    }
}
