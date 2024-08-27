package com.todo.todo.repository;

import com.todo.todo.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByTodoId(Long todoId);
    Page<TodoEntity>findAll(Pageable pageable);
}
