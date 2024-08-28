package com.todo.todo.service;

import com.todo.todo.dto.request.UserRequestDto;
import com.todo.todo.dto.response.UserResponseDto;
import com.todo.todo.entity.TodoEntity;
import com.todo.todo.entity.UserEntity;
import com.todo.todo.entity.UserTodoAssignment;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.UserRepository;
import com.todo.todo.repository.UserTodoAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserTodoAssignmentRepository userTodoAssignmentRepository;
    private final TodoRepository todoRepository;


    public UserService(UserRepository userRepository, UserTodoAssignmentRepository userTodoAssignmentRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.userTodoAssignmentRepository = userTodoAssignmentRepository;
        this.todoRepository = todoRepository;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity user = new UserEntity(userRequestDto);
        UserEntity savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto(user))
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("아이디를 찾을 수 없습니다"));
        return new UserResponseDto(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("사용자를 찾을 수 없습니다");
        } else {
            userRepository.deleteById(id);
        }
    }

    public void assignUserToTodo(Long userId, Long todoId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("유저를 찾을 수 없습니다: " + userId));
        TodoEntity todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("일정을 찾을 수 없습니다: " + todoId));

        UserTodoAssignment assignment = new UserTodoAssignment(user, todo);
        userTodoAssignmentRepository.save(assignment);
    }
}

