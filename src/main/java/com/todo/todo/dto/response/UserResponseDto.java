package com.todo.todo.dto.response;

import com.todo.todo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(UserEntity user) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
