package com.todo.todo.dto.response;

import com.todo.todo.entity.TodoEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TodoResponseDto {

    private Long id;
    private String userName;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private int commentCount;
    private List<UserResponseDto> assignedUsers;



    public TodoResponseDto(TodoEntity todoEntity , boolean includeUserDetails) {
        this.id = todoEntity.getTodoId();
        this.userName = todoEntity.getUserName();
        this.title = todoEntity.getTitle();
        this.contents = todoEntity.getContents();
        this.createdAt = todoEntity.getCreatedAt();
        this.commentCount = todoEntity.getComments().size();

        if (includeUserDetails) {
            this.assignedUsers = todoEntity.getAssignments().stream()
                    .map(assignment -> new UserResponseDto(assignment.getUser()))
                    .collect(Collectors.toList());
        }
    }

    public TodoResponseDto(TodoEntity todoEntity){
        this(todoEntity, false);
    }
}
