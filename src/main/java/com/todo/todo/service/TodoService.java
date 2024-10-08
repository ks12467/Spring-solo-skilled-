package com.todo.todo.service;

import com.todo.todo.dto.request.TodoRequestDto;
import com.todo.todo.dto.response.TodoResponseDto;
import com.todo.todo.entity.TodoEntity;
import com.todo.todo.entity.UserEntity;
import com.todo.todo.entity.UserTodoAssignment;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.UserRepository;
import com.todo.todo.repository.UserTodoAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final UserTodoAssignmentRepository userTodoAssignmentRepository;


    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository, UserTodoAssignmentRepository userTodoAssignmentRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.userTodoAssignmentRepository = userTodoAssignmentRepository;
    }

    public Page<TodoResponseDto> getTodoPages(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<TodoEntity> todoPage = todoRepository.findAll(pageable);
        return todoPage.map(TodoResponseDto::new);
    }




    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto){
        UserEntity user = userRepository.findById(todoRequestDto.getUserId()).orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        TodoEntity todo = new TodoEntity(todoRequestDto, user);
        todo.setUser(user);
        TodoEntity saved = todoRepository.save(todo);

        UserTodoAssignment assignment = new UserTodoAssignment(user, saved);
        userTodoAssignmentRepository.save(assignment);
        return new TodoResponseDto(saved);
    }


    public TodoResponseDto getTodoById(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("아이디를 찾을 수 없습니다 " + id));
        return new TodoResponseDto(todo, true);  // 단건 조회 시 유저 정보 포함
    }


    public Long updateTodo(Long id, TodoRequestDto todoRequestDto) {
        Optional<TodoEntity> optionalTodo = todoRepository.findById(id);

        if(optionalTodo.isPresent()){
            TodoEntity todo = optionalTodo.get();
            todo.update(todoRequestDto);
            todo.setCreatedAt(LocalDateTime.now());
            todoRepository.save(todo);
            return id;
        } else {
            throw new NoSuchElementException("아이디를 찾지 못했습니다 " + id);
        }
    }


    public void deleteTodo(Long id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("아이디가 일치하지 않아 일정 삭제가 불가능합니다.");
        }
    }


    public List<TodoEntity> findByTodoId(Long id) {
        return todoRepository.findByTodoId(id);
    }
}
