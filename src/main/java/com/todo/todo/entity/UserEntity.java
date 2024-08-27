package com.todo.todo.entity;

import com.todo.todo.dto.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false, length = 20)
    private String userName;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @OneToMany
    private List<TodoEntity> todos;

    public UserEntity(UserRequestDto userRequestDto) {
        this.userName = userRequestDto.getUsername();
        this.email = userRequestDto.getEmail();
        this.createDate = LocalDateTime.now();
        this.updateDate = this.createDate;
    }

    public void update(UserRequestDto userRequestDto) {
        this.userName = userRequestDto.getUsername();
        this.email = userRequestDto.getEmail();
        this.updateDate = LocalDateTime.now();
    }
}
