package com.todo.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.todo.dto.request.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;
    @Column(nullable = false,length = 20)
    private String userName;
    @Column(nullable = false,length = 50)
    private String title;
    @Column(nullable = false,length = 500)
    private String contents;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTodoAssignment> assignments = new ArrayList<>(); // N:M 관계를 위한 중간 엔티티


    public TodoEntity(TodoRequestDto todoRequestDto) {
        this.userName = todoRequestDto.getUserName();
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.user = user;

    }

    public void update(TodoRequestDto todoRequestDto) {
        this.userName = todoRequestDto.getUserName();
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.updatedAt = LocalDateTime.now();
    }

}
