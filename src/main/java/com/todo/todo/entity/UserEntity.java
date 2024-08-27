package com.todo.todo.entity;

import com.todo.todo.dto.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoEntity> todos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTodoAssignment> assignments = new ArrayList<>();

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
