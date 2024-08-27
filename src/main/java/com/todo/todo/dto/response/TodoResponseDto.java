package com.todo.todo.dto.response;

import com.todo.todo.entity.TodoEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {
    private Long id;
    private String userName;
    private String title;
    private String contents;
    private LocalDateTime createdAt;


    public TodoResponseDto(TodoEntity todoEntity) {
        this.userName = todoEntity.getUserName();
        this.title = todoEntity.getTitle();
        this.contents = todoEntity.getContents();
        this.createdAt = todoEntity.getCreatedAt();
    }
}
