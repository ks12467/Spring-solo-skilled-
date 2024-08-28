package com.todo.todo.controller;


import com.todo.todo.dto.request.TodoRequestDto;
import com.todo.todo.dto.response.TodoResponseDto;
import com.todo.todo.entity.TodoEntity;
import com.todo.todo.service.TodoService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.createTodo(todoRequestDto);
    }

    @PutMapping("/todo/update/{id}")
    public Long updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {
        return todoService.updateTodo(id, todoRequestDto);
    }

    @GetMapping("/todo/{id}")
    public TodoResponseDto getByTodoId(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @GetMapping("/todo/page")
    public Page<TodoResponseDto> getPageTodo(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size
    ){
        return todoService.getTodoPages(page,size);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }
}
