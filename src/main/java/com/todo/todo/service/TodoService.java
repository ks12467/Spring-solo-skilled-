package com.todo.todo.service;

import com.todo.todo.dto.request.TodoRequestDto;
import com.todo.todo.dto.response.TodoResponseDto;
import com.todo.todo.entity.TodoEntity;
import com.todo.todo.repository.TodoRepository;
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

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Page<TodoResponseDto> getTodoPages(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<TodoEntity> todoPage = todoRepository.findAll(pageable);
        return todoPage.map(TodoResponseDto::new);
    }


    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto){
        TodoEntity todo = new TodoEntity(todoRequestDto);
        TodoEntity saved = todoRepository.save(todo);
        return new TodoResponseDto(saved);
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
