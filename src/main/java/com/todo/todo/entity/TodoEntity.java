package com.todo.todo.entity;

import com.todo.todo.dto.request.TodoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "todo")
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;
    @Column(nullable = false,length = 20)
    private String userName;
    @Column(nullable = false,length = 50)
    private String title;
    @Column(nullable = false,length = 500)
    private String contents;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TodoEntity(TodoRequestDto todoRequestDto) {
        this.userName = todoRequestDto.getUserName();
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public void update(TodoRequestDto todoRequestDto) {
        this.userName = todoRequestDto.getUserName();
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.updatedAt = LocalDateTime.now();
    }

}
