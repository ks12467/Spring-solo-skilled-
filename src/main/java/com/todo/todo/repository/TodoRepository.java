package com.todo.todo.repository;

import com.todo.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByUserId(Long id);
}
